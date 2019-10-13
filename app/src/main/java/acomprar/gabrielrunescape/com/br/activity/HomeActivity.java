package acomprar.gabrielrunescape.com.br.activity;

import java.util.Date;
import java.util.List;
import android.util.Log;
import android.view.Menu;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.Toast;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.*;
import androidx.recyclerview.widget.*;
import acomprar.gabrielrunescape.com.br.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import acomprar.gabrielrunescape.com.br.adapter.RendimentoAdapter;
import acomprar.gabrielrunescape.com.br.model.SimpleDividerItemDecoration;
import acomprar.gabrielrunescape.com.br.object.Categoria;
import acomprar.gabrielrunescape.com.br.object.Rendimento;

/**
 * Cria e exibe todos os elementos (views) necessários na tela inicial (HomeActivity).
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2017-07-08
 */
public class HomeActivity extends AppCompatActivity {
    //private RendimentoDAO dao;
    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;
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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.content_home);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        swipeContainer.setColorSchemeResources(R.color.colorPrimary, android.R.color.darker_gray, android.R.color.holo_green_dark);
    }

    @Override
    /**
     * (Re)carrega todos os elementos da view após criar, pausar ou destruir a ActivityView.
     */
    protected void onResume() {
        super.onResume();

        try {
            updateData();
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
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

    public void updateData() {
        try {
            /**dao = new RendimentoDAO(this);
            dao.open(false);

            final RendimentoAdapter adapter = new RendimentoAdapter(dao.getAll(), getFragmentManager());
            adapter.notifyDataSetChanged();

            recyclerView.setAdapter(adapter);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeContainer.setRefreshing(false);

                    adapter.clear();
                    adapter.addAll(dao.getAll());
                }
            }); */

            final List<Rendimento> lista = new ArrayList<>();

            lista.add(new Rendimento(1002, "Compra", 24.4, new Date(2019, 10, 10), new Date(2019, 10, 10), new Categoria(1, "Gasto", "-")));
            lista.add(new Rendimento(1003, "Venda", 44.34, new Date(2019, 8, 17), new Date(2019, 8, 17), new Categoria(2, "Receita", "+")));
            lista.add(new Rendimento(1005, "Compra K-bulosa", 214, new Date(2019, 8, 12), new Date(2019, 8, 12), new Categoria(1, "Gasto", "-")));
            lista.add(new Rendimento(1006, "Venda da NASA", 262, new Date(2019, 8, 18), new Date(2019, 8, 18), new Categoria(2, "Receita", "+")));
            lista.add(new Rendimento(1012, "Mercadin' do baiano", 12.53, new Date(2019, 4, 2), new Date(2019, 4, 2), new Categoria(4, "Besteira", "-")));
            lista.add(new Rendimento(1013, "Claro que não é venda", 102.92, new Date(2019, 4, 22), new Date(2019, 8, 17), new Categoria(1, "Gasto", "-")));
            lista.add(new Rendimento(1025, "Agora pode talvez ser uma venda", 87.75, new Date(2019, 8, 18), new Date(2019, 8, 18), new Categoria(2, "Venda", "+")));

            final RendimentoAdapter adapter = new RendimentoAdapter(lista, getFragmentManager());
            adapter.notifyDataSetChanged();

            recyclerView.setAdapter(adapter);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeContainer.setRefreshing(false);

                    adapter.clear();
                    adapter.addAll(lista);
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
