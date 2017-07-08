package acomprar.gabrielrunescape.com.br.activity;

import android.os.Bundle;
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
    @Override
    /**
     * Cria e carrega todos os elementos ao exibir a view.
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
    }
}
