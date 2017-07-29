package acomprar.gabrielrunescape.com.br.adapter;

import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.app.FragmentManager;
import acomprar.gabrielrunescape.com.br.R;
import android.support.v7.widget.RecyclerView;
import acomprar.gabrielrunescape.com.br.object.Item;
import acomprar.gabrielrunescape.com.br.activity.HomeActivity;

/**
 * Carrega os itens básicos para criar os ItemView da RecycleView.
 *
 * @author Gabriel Filipe
 * @version A!
 * @since 2017-07-29
 */
public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Item item;
    public TextView nome, status;
    public FragmentManager support;
    private static String TAG = ItemHolder.class.getSimpleName();

    /**
     * Método construtor da classe.
     *
     * @param v Activity da RecycleView.
     */
    public ItemHolder(View v) {
        super(v);

        nome = (TextView) v.findViewById(R.id.txt_nome);
        status = (TextView) v.findViewById(R.id.txt_status);

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
                intent.putExtra("Item", item);

                v.getContext().startActivity(intent);
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
