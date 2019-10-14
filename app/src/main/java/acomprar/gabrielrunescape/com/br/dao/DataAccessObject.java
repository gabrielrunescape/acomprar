package acomprar.gabrielrunescape.com.br.dao;

import android.util.Log;
import android.database.*;
import android.content.Context;
import android.database.sqlite.*;
import acomprar.gabrielrunescape.com.br.database.*;

/**
 * Objeto do tipo DAO para realizar conexões com o banco de dados.
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2019-10-13
 */
public class DataAccessObject {
    private Context context;
    private SQLiteDatabase database;
    protected CustomSQLiteOpenHelper helper;
    private static String TAG = DataAccessObject.class.getSimpleName();

    /**
     * Método construtor da classe.
     *
     * @param c Contexto da aplicação.
     */
    public DataAccessObject(Context c) {
        context = c;

        try {
            Log.i(TAG, "Lendo banco banco de dados ...");
            helper = new CustomSQLiteOpenHelper(c, "acomprar_dbase", VersionUtils.getVersionCode(c));
        } catch (SQLException ex) {
            Log.e(TAG, "Erro ao ler o banco de dados.\n" + ex.getMessage());
        }
    }

    /**
     * Abre o banco de dados.
     *
     * @param writable Falso para somente leitura.
     */
    public SQLiteDatabase open(boolean writable) {
        try {
            Log.i(TAG, "Abrindo banco de dados ...");

            if (writable) {
                database = helper.getWritableDatabase();
            } else {
                database = helper.getReadableDatabase();
            }
        } catch (SQLException ex) {
            Log.e(TAG, "Erro ao abrir o banco de dados.\n" + ex.getMessage());
        }

        return database;
    }

    /**
     * Fecha o banco de dados se estiver em aberto.
     */
    @Override
    protected void finalize() {
        try {
            super.finalize();

            if (database != null && database.isOpen()) {
                Log.i(TAG, "Finalizando banco de dados.");
                database.close();
            }
        } catch (Throwable ex) {
            Log.e(TAG, "Erro ao finalizar o banco de dados.\n" + ex.getMessage());
        }
    }
}
