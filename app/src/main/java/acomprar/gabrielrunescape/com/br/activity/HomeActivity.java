package acomprar.gabrielrunescape.com.br.activity;

import java.util.*;

import android.content.Intent;
import android.util.*;
import android.view.*;
import android.os.Bundle;
import android.widget.*;
import androidx.recyclerview.widget.*;
import androidx.appcompat.widget.Toolbar;
import acomprar.gabrielrunescape.com.br.R;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import acomprar.gabrielrunescape.com.br.dao.RendimentoDAO;
import acomprar.gabrielrunescape.com.br.object.Rendimento;
import acomprar.gabrielrunescape.com.br.utils.DateCurrent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import acomprar.gabrielrunescape.com.br.adapter.RendimentoAdapter;
import acomprar.gabrielrunescape.com.br.model.SimpleDividerItemDecoration;

/**
 * Cria e exibe todos os elementos (views) necessários na tela inicial (HomeActivity).
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2017-07-08
 */
public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RendimentoDAO dao;
    private List<Rendimento> list;
    private TextView txt_month_year;
    private RendimentoAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView img_back, img_next;
    private SwipeRefreshLayout swipeContainer;
    private FloatingActionButton floatingActionButton;
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
        floatingActionButton = findViewById(R.id.floatingButton);

        img_back = findViewById(R.id.img_back);
        img_next = findViewById(R.id.img_next);
        txt_month_year = findViewById(R.id.txt_month_year);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        swipeContainer.setColorSchemeResources(R.color.colorPrimary, android.R.color.darker_gray, android.R.color.holo_green_dark);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RendimentoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    /**
     * (Re)carrega todos os elementos da view após criar, pausar ou destruir a ActivityView.
     */
    protected void onResume() {
        super.onResume();

        try {
            DateCurrent dc = new DateCurrent(this, recyclerView);

            img_back.setOnClickListener(dc);
            img_next.setOnClickListener(dc);

            list = dc.updateData();

            adapter = new RendimentoAdapter(list, getFragmentManager());
            adapter.notifyDataSetChanged();

            recyclerView.setAdapter(adapter);
            swipeContainer.setOnRefreshListener(this);
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
            list = dao.getAll();

            adapter = new RendimentoAdapter(list, getFragmentManager());
            adapter.notifyDataSetChanged();

            recyclerView.setAdapter(adapter);
            swipeContainer.setOnRefreshListener(this);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    public void updateData(String query) {
        try {
            dao = new RendimentoDAO(this, false);
            list = dao.getRendimentosbyDescricao(query);

            adapter = new RendimentoAdapter(list, getFragmentManager());
            adapter.notifyDataSetChanged();

            recyclerView.setAdapter(adapter);
            swipeContainer.setOnRefreshListener(this);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    @Override
    public void onRefresh() {
        swipeContainer.setRefreshing(false);

        adapter.clear();
        adapter.addAll(list);
    }
}
