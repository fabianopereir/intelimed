package nutes.intelimed.model;

import nutes.intelimed.model.DAO.UserDao;
import nutes.intelimed.model.helper.DatabaseHelper;
import android.content.Context;

public class UserScript extends UserDao{
	
	private DatabaseHelper dbHelper;

	/**
	 * Acessa o banco de dados com um script SQL
	 * @param ctx - contexto que será criado o banco
	 */
	public UserScript(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, BaseScript.NOME_BANCO, BaseScript.VERSAO_BANCO,
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

