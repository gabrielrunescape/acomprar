package acomprar.gabrielrunescape.com.br.adapter;

import java.text.SimpleDateFormat;
import java.util.List;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import acomprar.gabrielrunescape.com.br.R;
import androidx.recyclerview.widget.RecyclerView;
import acomprar.gabrielrunescape.com.br.object.Rendimento;

/**
 * A classe extende um ArrayAdapter no qual é utilizado para inserir informações de um objeto a ele
 * exibindo em uma RecyclerView.
 *
 * @author Gabriel Filipe
 * @version A1
 * @since 2017-07-29
 */
public class RendimentoAdapter extends RecyclerView.Adapter<RendimentoHolder> {
    private List<Rendimento> listItens;
    private FragmentManager support;
    private static String TAG = RendimentoAdapter.class.getSimpleName();

    /**
     * Metódo construtor da classe.
     *
     * @param lista ArrayList serializado com Item.
     */
    public RendimentoAdapter(List<Rendimento> lista, FragmentManager support) {
        this.listItens = lista;
        this.support = support;
    }

    /**
     * Cria o layout de cada item do RecyclerVeiw.
     *
     * @param parent RecyclerView.
     * @param viewType tipo da view.
     *
     * @return Um adaptador de RecyclerView.
     */
    @Override
    public RendimentoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);

        return new RendimentoHolder(itemView);
    }

    /**
     * Define os valores de cada Textview dentro do list_item.xml.
     *
     * @param holder Adaptador de RecyclerView.
     * @param position Posição o qual está preenchendo.
     */
    @Override
    public void onBindViewHolder(RendimentoHolder holder, int position) {
        try {
            Rendimento item = listItens.get(position);

            holder.rendimento = item;
            holder.support = support;

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd");
            String vencimento = sdf.format(item.getDT_Compra());

            holder.categoria.setText(item.getCategoria().getNome());
            holder.valor.setText(String.format("R$ %1$,.2f", item.getValor()));
            holder.vencimento.setText(vencimento.substring(0, 1).toUpperCase() + vencimento.substring(1));
            holder.descricao.setText(item.getDescricao());


            switch (item.getCategoria().getTipo()) {
                case "-":
                    holder.valor.setTextColor(holder.valor.getResources().getColor(R.color.colourDanger));
                    break;
                case "0":
                    holder.valor.setTextColor(holder.valor.getResources().getColor(R.color.colourWarning));
                    break;
                case "+":
                    holder.valor.setTextColor(holder.valor.getResources().getColor(R.color.colourSuccess));
                    break;
                case "1":
                default:
                    holder.valor.setTextColor(holder.valor.getResources().getColor(R.color.colourInformation));
                    break;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    /**
     * @return Número de elementos no RecyclerView.
     */
    @Override
    public int getItemCount() {
        return listItens.size();
    }

    /**
     * Adiciona todos os objetos Item em uma lista.
     *
     * @param list Lista com os objetos do tipo Item.
     */
    public void addAll(List<Rendimento> list) {
        listItens.addAll(list);

        notifyDataSetChanged();
    }

    /**
     * Limpa todos os valores da lista de itens do recyclerView.
     */
    public void clear() {
        listItens.clear();

        notifyDataSetChanged();
    }

    /**
     * Remove o Item da lista.
     *
     * @param item Item a ser removido.
     */
    public void removeItem(Rendimento item) {
        int position = listItens.indexOf(item);

        listItens.remove(position);
        notifyItemRemoved(position);
    }
}
