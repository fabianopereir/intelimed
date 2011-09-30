package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.model.DAO.AnswersDao;
import nutes.intelimed.model.helper.DatabaseHelper;

/**
 * Classe responsável pela criação do script da tabela resposta 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class AnswerScript extends AnswersDao{
	
	private DatabaseHelper dbHelper;

	/**
	 * Acessa o banco de dados com um script SQL
	 * @param ctx - contexto que será criado o banco
	 */
	public AnswerScript(Context ctx) {
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
