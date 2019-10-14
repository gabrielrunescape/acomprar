package acomprar.gabrielrunescape.com.br.dao;

import java.util.*;
import android.util.Log;
import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import acomprar.gabrielrunescape.com.br.object.*;
import acomprar.gabrielrunescape.com.br.database.*;

/**
 * Classe responsável por todos os eventos de inserir, alterar, apagar e exibir
 * todos os itens.
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2019-10-13
 */
public class RendimentoDAO {
    private Context context;
    private SQLiteDatabase database;
    private static String TAG = Rendimento.class.getSimpleName();

    private static String query = "SELECT " +
        "R.ID, R.Descricao, R.Valor, C.ID AS `Categoria`, C.Nome, C.Tipo, R.Dt_Criacao, R.Dt_Compra " +
        "FROM `Rendimento` R INNER JOIN `Categoria` C ON C.ID = R.Categoria";

    /**
     * Método construtor da classe.
     *
     * @param c Contexto da aplicação.
     */
    public RendimentoDAO(Context c, boolean writable) {
        context = c;

        try {
            DataAccessObject dao = new DataAccessObject(c);
            database = dao.open(writable);
        } catch (SQLException ex) {
            Log.e(TAG, "Erro ao ler o banco de dados.\n" + ex.getMessage());
        }
    }

    /**
     * Apaga os itens da tabela Rendimento com a ID.
     *
     * @param id Código identificador do Item.
     */
    public void delete(int id) {
        database.delete("`Rendimento`", "ID = " + id, null);

        Log.i(TAG, "Apagado com sucesso!");
    }

    /**
     * Insere um novo Rendimento.
     *
     * @param r Objeto Rendimento.
     * @return Uma classe Rendimento já populada.
     */
    public Rendimento insert(Rendimento r) {
        ContentValues values = new ContentValues();

        try {
            values.put("Descricao", r.getDescricao());
            values.put("Valor", r.getValor());
            values.put("Categoria", r.getCategoria().getID());
            values.put("Dt_compra", r.getDateISO(r.getDT_Compra()));

            Log.i(TAG, "Inserindo item ... ");
            long id = database.insert("`Rendimento`", null, values);

            Cursor cursor = database.rawQuery(query + " WHERE ID = " + id, null);
            cursor.moveToFirst();

            Rendimento rendimento = new Rendimento(cursor);
            cursor.close();

            Log.i(TAG, "Rendimento inserido com sucessso!");

            return rendimento;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());

            return null;
        }
    }

    /**
     * Altera um Rendimento já existente.
     *
     * @param r Objeto REndimento com as novas informações.
     * @return O resultado da operação.
     */
    public Rendimento update(Rendimento r) {
        ContentValues values = new ContentValues();

        try {
            values.put("Descricao", r.getDescricao());
            values.put("Valor", r.getValor());
            values.put("Categoria", r.getCategoria().getID());
            values.put("Dt_compra", r.getDateISO(r.getDT_Compra()));

            Log.i(TAG, "Alterando item ... ");
            database.update("`Rendimento`", values, "ID = " + r.getID(), null);

            Cursor cursor = database.rawQuery(query + " WHERE ID = " + r.getID() , null);
            cursor.moveToFirst();

            Rendimento rendimento = new Rendimento(cursor);
            cursor.close();

            Log.i(TAG, "Rendimento alterado com sucessso!");

            return rendimento;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());

            return null;
        }
    }

    /**
     * Obtem todos os registros de transações dentro do banco de dados.
     *
     * @return uma lista do tipo Transaction.
     */
    public List<Rendimento> getAll() {
        List<Rendimento> itens = new ArrayList<>();

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            while (!cursor.isAfterLast()) {
                itens.add(new Rendimento(cursor));
                cursor.moveToNext();

                Log.i(TAG, "Obtendo itens ...");
            }

            cursor.close();
            return itens;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());

            cursor.close();
            return null;
        }
    }
}
