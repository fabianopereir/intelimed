package nutes.intelimed.model.helper;

import nutes.intelimed.model.BaseScript;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Classe utilitária para abrir, criar, e atualizar o banco de dados.Responsável pela implementacao de SQLiteOpenHelper. 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String CATEGORIA = "nutes";

	private String[] scriptSQLCreate;
	private String[] scriptSQLDelete;
	private static DatabaseHelper helper;

	/**
	 * Método construtor - Cria uma instância de DatabaseHelper
	 * @param context - contexto onde será usado os dados do banco de dados
	 * @param nomeBanco - nome do banco de dados
	 * @param versaoBanco - versão do banco de dados (se for diferente atualiza)
	 * @param scriptSQLCreate - SQL com o create table
	 * @param scriptDatabaseDelete - SQL com o drop table
	 */
	private DatabaseHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptDatabaseDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptDatabaseDelete;
	}
	
	public static DatabaseHelper getInstance(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptDatabaseDelete){
		if(helper==null){
			return new DatabaseHelper(context, nomeBanco, versaoBanco,
					BaseScript.getScriptDatabaseCreate(), BaseScript.getScriptDatabaseDelete());
		}
		return helper;
	}
	
	/**
	 * Método onCreate - Cria banco de dados
	 * @param SQLiteDatabase db
	 * 
	 */
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
	
	/**
	 * Método onCreate - Atualiza banco de dados
	 * @param SQLiteDatabase db
	 * @param int versaoAntiga
	 * @param int novaVersao
	 */
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