package nutes.intelimed.model.DAO;

import nutes.intelimed.model.IModelEvidence;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.Evidence.EvidenceTable;
import nutes.intelimed.model.entity.Node.NodeTable;
import android.content.ContentValues;
import android.content.Context;

/**
 * Classe responsável por realizar manipulação em banco na tabela de evidências
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
	 * Insere nova evidência no banco
	 * @param Evidence - evidência a ser inserida no banco
	 * @return id da evidência
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
	 * Deleta uma evidência na base de dados
	 * @param Long id (identificador da evidência)
	 * @return int - quantidade de evidências deletadas
	 */
	public int deleteEvidence(long id) {
		String where = NodeTable._ID + "=?";

		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };

		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	@Override
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
