package acomprar.gabrielrunescape.com.br.activity;

import java.util.*;
import android.util.*;
import android.view.*;
import android.os.Bundle;
import androidx.recyclerview.widget.*;
import androidx.appcompat.widget.Toolbar;
import acomprar.gabrielrunescape.com.br.R;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import acomprar.gabrielrunescape.com.br.dao.RendimentoDAO;
import acomprar.gabrielrunescape.com.br.object.Rendimento;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import acomprar.gabrielrunescape.com.br.adapter.RendimentoAdapter;
import acomprar.gabrielrunescape.com.br.model.SimpleDividerItemDecoration;

/**
 * Cria e exibe todos os elementos (views) necessários na tela inicial (HomeActivity).
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2017-07-08
 */
public class HomeActivity extends AppCompatActivity {
    private RendimentoDAO dao;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        swipeContainer = (findViewById(R.id.content_home));
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

        final MenuItem searchItem = menu.findItem(R.id.item_search);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                searchItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    updateData(newText);
                } else {
                    updateData();
                }

                return true;
            }
        });

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

        return super.onOptionsItemSelected(item);
    }

    public void updateData() {
        try {
            dao = new RendimentoDAO(this, false);

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
            });
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    public void updateData(final String query) {
        try {
            dao = new RendimentoDAO(this, false);

            final RendimentoAdapter adapter = new RendimentoAdapter(dao.getRendimentosby(query), getFragmentManager());
            adapter.notifyDataSetChanged();

            recyclerView.setAdapter(adapter);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeContainer.setRefreshing(false);

                    adapter.clear();
                    adapter.addAll(dao.getRendimentosby(query));
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
