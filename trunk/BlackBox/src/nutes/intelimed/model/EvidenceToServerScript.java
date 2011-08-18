package nutes.intelimed.model;

import nutes.intelimed.model.DAO.EvidenceToServerDao;
import nutes.intelimed.model.helper.DatabaseHelper;
import android.content.Context;

/**
 * Classe responsável pela criação do script da tabela evidencia
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 *  
 */
public class EvidenceToServerScript extends EvidenceToServerDao{
	
	private DatabaseHelper dbHelper;

	/**
	 * Cria o banco de dados com um script SQL
	 * @param ctx - contexto que será criado o banco
	 */
	public EvidenceToServerScript(Context ctx) {
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