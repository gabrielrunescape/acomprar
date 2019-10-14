package acomprar.gabrielrunescape.com.br.adapter;

import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.app.FragmentManager;
import acomprar.gabrielrunescape.com.br.R;
import androidx.recyclerview.widget.RecyclerView;
import acomprar.gabrielrunescape.com.br.object.Rendimento;
import acomprar.gabrielrunescape.com.br.activity.HomeActivity;

/**
 * Carrega os itens básicos para criar os ItemView da RecycleView.
 *
 * @author Gabriel Filipe
 * @version A1
 * @since 2017-07-29
 */
public class RendimentoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Rendimento rendimento;
    public FragmentManager support;
    public TextView categoria, descricao, vencimento, valor;
    private static String TAG = RendimentoHolder.class.getSimpleName();

    /**
     * Método construtor da classe.
     *
     * @param v Activity da RecycleView.
     */
    public RendimentoHolder(View v) {
        super(v);

        valor = (TextView) v.findViewById(R.id.txt_valor);
        descricao = (TextView) v.findViewById(R.id.txt_descricao);
        categoria = (TextView) v.findViewById(R.id.txt_categoria);
        vencimento = (TextView) v.findViewById(R.id.txt_vencimento);

        v.setOnClickListener(this);
    }

    @Override
    /**
     * Ao clicar no item, realiza uma ação.
     *
     * @param v Item do RecyclerView clicado.
     */
    public void onClick(View v) {
        try {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Log.i(TAG, String.format("O item nº %1$d foi clicado!", position + 1));

                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                intent.putExtra("Rendimento", rendimento);

                v.getContext().startActivity(intent);
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
