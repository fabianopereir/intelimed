package nutes.intelimed.model.DAO;

import nutes.intelimed.model.IModelEvidenceAnswers;
import nutes.intelimed.model.entity.EvidenceAnswers;
import nutes.intelimed.model.entity.EvidenceAnswers.EvidenceAnswersTable;
import nutes.intelimed.model.entity.Node.NodeTable;
import android.content.ContentValues;
import android.content.Context;


/**
 * Classe respons�vel por realizar manipula��o em banco na tabela de evid�ncias
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceAnswersDao extends GenericDao implements IModelEvidenceAnswers {
	
	public static final String NOME_TABELA = "evidencia_respostas";

	public EvidenceAnswersDao() {}

	public EvidenceAnswersDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	/**
	 * Insere nova evid�ncia no banco
	 * @param EvidenceAnswers - respostas da evid�ncia a ser inserida no banco
	 * @return id - identificador de evid�ncia
	 */
	public long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers) {
		ContentValues values = new ContentValues();
		values.put(EvidenceAnswersTable.FK_IDEVIDENCIA, evidenceAnswers.getFk_idevidencia());

		values.put(EvidenceAnswersTable.FK_IDRESPOSTA, evidenceAnswers.getFk_idResposta());
		
		
		long id = db.insert(NOME_TABELA, "", values);
		return id;
	}
	
	/**
	 * Deleta respostas de uma evid�ncia na base de dados
	 * @param Long id (identificador da tupla de respostas de uma evid�ncia)
	 * @return int - quantidade de tuplas de respostas de uma evid�ncias deletadas
	 */
	public int deleteEvidenceAnswers(long id) {
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
