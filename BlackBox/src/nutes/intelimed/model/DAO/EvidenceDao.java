package nutes.intelimed.model.DAO;

import nutes.intelimed.model.IModelEvidence;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.Evidence.EvidenceTable;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

/**
 * Classe respons�vel por realizar manipula��o em banco na tabela de evid�ncias
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceDao extends GenericDao implements IModelEvidence {
	
	public static final String NOME_TABELA = "evidencia";

	public EvidenceDao() {}

	public EvidenceDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	/**
	 * Insere nova evid�ncia no banco
	 * @param Evidence - evid�ncia a ser inserida no banco
	 * @return id da evid�ncia
	 */
	public long insertEvidence(Evidence evidence) {
		ContentValues values = new ContentValues();
		values.put(EvidenceTable.JUSTIFICATIVA, evidence.getJustificativa());
		values.put(EvidenceTable.MEDICO, evidence.getMedico());
		values.put(EvidenceTable.SISTEMA, evidence.getSistema());
		
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
			String sql = "DELETE FROM "+ NOME_TABELA;
	        db.execSQL(sql);
		}catch (Exception e) {
            aux=false;
            Log.i("Exception excluir",e.getMessage().toString());
		}
		
		return aux;
	}
	
	@Override
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
