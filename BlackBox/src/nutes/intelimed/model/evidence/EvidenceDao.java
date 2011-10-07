package nutes.intelimed.model.evidence;

import nutes.intelimed.model.BaseScript;
import nutes.intelimed.model.DatabaseHelper;
import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.evidence.Evidence.EvidencesTableConstants;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

/**
 * Classe respons�vel por realizar manipula��o em banco na tabela de evid�ncias
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceDao extends GenericDao implements IModelEvidenceDao {
	
	private DatabaseHelper dbHelper;
	
	public static final String NOME_TABELA = "evidencia";

	public EvidenceDao(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, BaseScript.NOME_BANCO, BaseScript.VERSAO_BANCO,
				BaseScript.getScriptDatabaseCreate(), BaseScript.getScriptDatabaseDelete());
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * Insere nova evid�ncia no banco
	 * @param Evidence - evid�ncia a ser inserida no banco
	 * @return id da evid�ncia
	 */
	public long insertEvidence(Evidence evidence) {
		ContentValues values = new ContentValues();
		values.put(EvidencesTableConstants.JUSTIFICATIVA, evidence.getJustificativa());
		values.put(EvidencesTableConstants.MEDICO, evidence.getMedico());
		values.put(EvidencesTableConstants.SISTEMA, evidence.getSistema());
		
		long id = db.insert(NOME_TABELA, "", values);
		return id;
	}
	
	/**
	 * Deleta evid�ncia na base de dados
	 * @return boolean - se deletar retorna true
	 */
	public boolean deleteEvidence() {
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
