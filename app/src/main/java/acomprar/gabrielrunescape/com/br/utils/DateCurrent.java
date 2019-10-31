package acomprar.gabrielrunescape.com.br.utils;

import java.util.*;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import acomprar.gabrielrunescape.com.br.R;
import androidx.recyclerview.widget.RecyclerView;
import acomprar.gabrielrunescape.com.br.dao.RendimentoDAO;
import acomprar.gabrielrunescape.com.br.object.Rendimento;
import acomprar.gabrielrunescape.com.br.adapter.RendimentoAdapter;

/**
 * Classe responsável por eventos relacionados a tratamento de datas com objetos do tipo View.
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2019-10-27
 */
public class DateCurrent implements View.OnClickListener {
    private Calendar current;
    private Activity context;
    private RendimentoDAO dao;
    private TextView txt_month_year;
    private RecyclerView recyclerView;
    private static String TAG = DateCurrent.class.getSimpleName();

    /**
     * Construtor com paramêtros.
     *
     * @param activity Contexto no qual o objeto Activity se encontra.
     * @param recyclerView Objeto a ser tratado para exibição dos dados.
     */
    public DateCurrent(Activity activity, RecyclerView recyclerView) {
        this.context = activity;
        this.recyclerView = recyclerView;
        this.txt_month_year = context.findViewById(R.id.txt_month_year);

        this.current = Calendar.getInstance();
        txt_month_year.setText(showMonthYear(current));
    }

    /**
     * Construtor com paramêtros.
     *
     * @param activity Contexto no qual o objeto Activity se encontra.
     */
    public DateCurrent(Activity activity) {
        this.context = activity;
        this.txt_month_year = context.findViewById(R.id.txt_month_year);

        this.current = Calendar.getInstance();
        txt_month_year.setText(showMonthYear(current));
    }

    /**
     * Converte objeto Date para mês / ano.
     *
     * @param date Objeto com a data a ser formatada.
     * @return "Mês / ANO"
     */
    private String showMonthYear(Calendar date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("MMMM / yyyy");

        return dateformat.format(date.getTime()).toUpperCase();
    }

    /**
     * Converte objeto Date para formato de data tipo ISO.
     *
     * @param date Objeto com a data a ser formatada.
     * @return Data em formato ISO.
     */
    public String[] daysInterval(Calendar date) {
        String[] retorno = new String[2];
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        switch (date.get(Calendar.MONTH)) {
            case Calendar.FEBRUARY:
                date.set(Calendar.DAY_OF_MONTH, 29);
                retorno[1] = dateformat.format(date.getTime());
                break;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                date.set(Calendar.DAY_OF_MONTH, 30);
                retorno[1] = dateformat.format(date.getTime());
                break;
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                date.set(Calendar.DAY_OF_MONTH, 31);
                retorno[1] = dateformat.format(date.getTime());
                break;
        }

        date.set(Calendar.DAY_OF_MONTH, 1);
        retorno[0] = dateformat.format(date.getTime());

        return retorno;
    }

    /**
     * Sobreescreve método implantado de clicar com objeto.
     *
     * @param v Objeto do tipo View
     */
    @Override
    public void onClick(View v) {
        try {
            int month = current.get(Calendar.MONTH);

            if (v.getId() == R.id.img_back) {
                current.set(Calendar.MONTH, (month - 1));
            } else {
                current.set(Calendar.MONTH, (month + 1));
            }

            txt_month_year.setText(showMonthYear(current));

            updateData();
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    public List<Rendimento> updateData() {
        try {
            dao = new RendimentoDAO(context, false);
            List<Rendimento> list = dao.getRendimentosbyMonth(daysInterval(current));

            RendimentoAdapter adapter = new RendimentoAdapter(list, context.getFragmentManager());
            adapter.notifyDataSetChanged();

            recyclerView.setAdapter(adapter);

            return list;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());

            return null;
        }
    }
}
