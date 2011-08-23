package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.model.DAO.EdgeDao;
import nutes.intelimed.model.helper.DatabaseHelper;

/**
 * Classe respons�vel pela cria��o do script da tabela aresta
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 *  
 */
public class EdgeScript extends EdgeDao{
	
	private DatabaseHelper dbHelper;

	/**
	 * Cria o banco de dados com um script SQL
	 * @param ctx - contexto que ser� criado o banco
	 */
	public EdgeScript(Context ctx) {
		dbHelper = new DatabaseHelper(ctx, BaseScript.NOME_BANCO, BaseScript.VERSAO_BANCO,
				BaseScript.getScriptDatabaseCreate(), BaseScript.getScriptDatabaseDelete());
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}


}