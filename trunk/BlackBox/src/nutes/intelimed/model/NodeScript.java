package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.model.DAO.NodeDao;
import nutes.intelimed.model.helper.SQLiteHelper;

/**
 * Classe responsável pela criação do script da tabela no
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 *  
 */
public class NodeScript extends NodeDao{
	
	private SQLiteHelper dbHelper;

	/**
	 * Cria o banco de dados com um script SQL
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param ctx - contexto que será criado o banco
	 */
	public NodeScript(Context ctx) {
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
