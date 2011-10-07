package nutes.intelimed.model.evidence;

import nutes.intelimed.model.BaseScript;
import nutes.intelimed.model.DatabaseHelper;
import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.evidence.EvidenceAnswers.EvidenceAnswersTable;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;


/**
 * Classe responsável por realizar manipulação em banco na tabela de evidências
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceAnswersDao extends GenericDao implements IModelEvidenceAnswersDao {
	
	private DatabaseHelper dbHelper;
	
	public static final String NOME_TABELA = "evidencia_respostas";

	public EvidenceAnswersDao() {}

	public EvidenceAnswersDao(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, BaseScript.NOME_BANCO, BaseScript.VERSAO_BANCO,
				BaseScript.getScriptDatabaseCreate(), BaseScript.getScriptDatabaseDelete());
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * Insere nova evidência no banco
	 * @param EvidenceAnswers - respostas da evidência a ser inserida no banco
	 * @return id - identificador de evidência
	 */
	public long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers) {
		ContentValues values = new ContentValues();
		values.put(EvidenceAnswersTable.FK_IDEVIDENCIA, evidenceAnswers.getFk_idevidencia());

		values.put(EvidenceAnswersTable.FK_IDRESPOSTA, evidenceAnswers.getFk_idResposta());
		
		
		long id = db.insert(NOME_TABELA, "", values);
		return id;
	}
	
	/**
	 * Deleta respostas de evidências na base de dados
	 * @return boolean - se deletar retorna true
	 */
	public boolean deleteEvidenceAnswers() {
		boolean aux = true;
		try{
			db.delete(NOME_TABELA, null, null);
		}catch (Exception e) {
            aux=false;
            Log.i("Exception excluir",e.getMessage().toString());
		}
		
		return aux;
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

}
