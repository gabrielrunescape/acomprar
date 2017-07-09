package acomprar.gabrielrunescape.com.br.activity;

import android.util.Log;
import android.view.Menu;
import android.os.Bundle;
import android.widget.Toast;
import android.view.MenuItem;
import acomprar.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;

/**
 * Cria e exibe todos os elementos (views) necessários na tela inicial (HomeActivity).
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2017-07-08
 */
public class HomeActivity extends AppCompatActivity {
    private static String TAG = HomeActivity.class.getSimpleName();

    @Override
    /**
     * Cria e carrega todos os elementos ao exibir a view.
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Log.i(TAG, String.format("Criando views e demais elementos da activity %s.", TAG));
    }

    @Override
    /**
     * (Re)carrega todos os elementos da view após criar, pausar ou destruir a ActivityView.
     */
    protected void onResume() {
        super.onResume();
    }

    /**
     * Importa um menu para Toolbar padrão da aplicação.
     *
     * @param menu Toolbar da aplicação.
     * @return Menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    /**
     * Define uma escolha para MenuItem que foi clicado no Navigation Drawer.
     *
     * @param item MenuItem escolhido (clicado).
     * @return MenuItem.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item_add:
            case R.id.item_report:
            case R.id.item_about:
            default:
                Log.i(TAG, "Função não programada!");
                Toast.makeText(this, "Função não programada!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
