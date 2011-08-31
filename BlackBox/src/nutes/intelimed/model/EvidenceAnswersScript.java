package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.model.DAO.EvidenceAnswersDao;
import nutes.intelimed.model.helper.DatabaseHelper;

/**
 * Classe responsável pela criação do script da tabela evidencia 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class EvidenceAnswersScript extends EvidenceAnswersDao{
	
	private DatabaseHelper dbHelper;

	/**
	 * Acessa o banco de dados com um script SQL
	 * @param ctx - contexto que será criado o banco
	 */
	public EvidenceAnswersScript(Context ctx) {
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
