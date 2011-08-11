package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.model.DAO.EvidenceAnswersDao;
import nutes.intelimed.model.helper.SQLiteHelper;

/**
 * Classe respons�vel pela cria��o do script da tabela evidencia 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class EvidenceAnswersScript extends EvidenceAnswersDao{
	
	private SQLiteHelper dbHelper;

	/**
	 * Cria o banco de dados com um script SQL
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param ctx - contexto que ser� criado o banco
	 */
	public EvidenceAnswersScript(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, StructureQuestionnaireScript.NOME_BANCO, StructureQuestionnaireScript.VERSAO_BANCO,
				StructureQuestionnaireScript.getScriptDatabaseCreate(), StructureQuestionnaireScript.getScriptDatabaseDelete());
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
