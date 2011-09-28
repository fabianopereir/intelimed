package nutes.intelimed.model;

import nutes.intelimed.model.DAO.EvidenceServerDao;
import nutes.intelimed.model.helper.DatabaseHelper;
import android.content.Context;

/**
 * Classe responsável pela criação do script da tabela evidencia
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 *  
 */
public class EvidenceServerScript extends EvidenceServerDao{
	
	private DatabaseHelper dbHelper;

	/**
	 * Acessa o banco de dados com um script SQL
	 * @param ctx - contexto que será criado o banco
	 */
	public EvidenceServerScript(Context ctx) {
		if(this.db!=null)
			this.db.close();
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