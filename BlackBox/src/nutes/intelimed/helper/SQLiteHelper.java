package nutes.intelimed.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe responsável pela implementacao de SQLiteOpenHelper. 
 * Classe utilitária para abrir, criar, e atualizar o banco de dados.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	private static final String CATEGORIA = "nutes";

	private String[] scriptSQLCreate;
	private String[] scriptSQLDelete;

	/**
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versão do banco de dados (se for diferente atualiza)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptDatabaseDelete SQL com o drop table...
	 * @Description Cria uma instância de SQLiteHelper
	 */
	public SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptDatabaseDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptDatabaseDelete;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(CATEGORIA, "Criando banco com sql");
		int qtdeScripts = scriptSQLCreate.length;
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			Log.i(CATEGORIA, sql);
			db.execSQL(sql);
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		Log.w(CATEGORIA, "Atualizando da versão " + versaoAntiga + " para " + novaVersao + ". Todos os registros serão deletados.");
		int qtdeScripts = scriptSQLDelete.length;
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLDelete[i];
			Log.i(CATEGORIA, sql);
			db.execSQL(sql);
		}
		onCreate(db);
	}
}