package acomprar.gabrielrunescape.com.br.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import acomprar.gabrielrunescape.com.br.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Cria e exibe relacionados com informações do banco de dados sobre Rendimentos.
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2019-11-23
 */
public class RendimentoActivity extends AppCompatActivity {
    private Spinner spinner;

    @Override
    /**
     * Cria e carrega todos os elementos ao exibir a view.
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rendimento_activity);

        addItensSpinner();
    }

    public void addItensSpinner() {
        spinner = findViewById(R.id.spinner);

        List<String> lista = new ArrayList<String>();

        lista.add("Arroz");
        lista.add("Berinjela");
        lista.add("Carne");
        lista.add("Danoninho");
        lista.add("Esfirra");
        lista.add("Frango");
        lista.add("Gelatina");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }
}
