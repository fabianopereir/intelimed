package nutes.intelimed.model.DAO;

import java.util.ArrayList;

import nutes.intelimed.model.IModelEvidenceToServer;
import nutes.intelimed.model.entity.Answer;
import nutes.intelimed.model.entity.Answer.AnswersTable;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.Evidence.EvidenceTable;
import nutes.intelimed.model.entity.EvidenceAnswers;
import nutes.intelimed.model.entity.EvidenceAnswers.EvidenceAnswersTable;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class EvidenceToServerDao extends GenericDao implements IModelEvidenceToServer{
	public static final String NOME_TABELA = "evidencia_respostas INNER JOIN resposta"+"INNER JOIN evidencia";

	public EvidenceToServerDao() {}

	public EvidenceToServerDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	/**
	  * Método responsável pela captura do cursor
	  * @return Cursor - cursor para consulta ao banco de dados
	  */
	public Cursor getCursor() {
		try {
			Cursor cursor = db.query(NOME_TABELA, Evidence.colunas, EvidenceAnswersTable.FK_IDRESPOSTA + "=" + AnswersTable.IDRESPOSTA + "AND" + EvidenceTable.IDEVIDENCIA + "=" + EvidenceAnswersTable.FK_IDEVIDENCIA, null, null, null, null);
			return cursor;

		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	/**
	 * Busca evidência e suas respostas com justificativa do médico sobre o diagnóstico na base de dados para envio ao servidor
	 * @return ArrayList<Object>
	 */
	public ArrayList<Object> searchEvidenceToServer(){

		Cursor c = getCursor();
		
		ArrayList<Object> estrutura = new ArrayList<Object>();
		
		int idxIdevidencia = c.getColumnIndex(EvidenceTable.IDEVIDENCIA);
		int idxIdevidencia_respostas = c.getColumnIndex(EvidenceAnswersTable.IDEVIDENCIARESPOSTAS);
		int idxIdresposta = c.getColumnIndex(AnswersTable.IDRESPOSTA);
		int idxSistema = c.getColumnIndex(EvidenceTable.SISTEMA);
		int idxMedico = c.getColumnIndex(EvidenceTable.MEDICO);
		int idxJustificativa = c.getColumnIndex(EvidenceTable.JUSTIFICATIVA);
		int idxFkIdNo = c.getColumnIndex(AnswersTable.FK_IDNO);
		
		if (c.moveToFirst()) {
			do {
				Evidence evidence = new Evidence();
				EvidenceAnswers evidenceAnswers = new EvidenceAnswers();
				Answer answer= new Answer();

				estrutura.add(evidence);
				estrutura.add(evidenceAnswers);
				estrutura.add(answer);


				evidence.setIdevidencia(c.getLong(idxIdevidencia));
				evidenceAnswers.setIdevidencia_respostas(c.getLong(idxIdevidencia_respostas));
				answer.setIdresposta(c.getLong(idxIdresposta));
				evidence.setSistema(c.getString(idxSistema));
				evidence.setMedico(c.getString(idxMedico));
				evidence.setJustificativa(c.getString(idxJustificativa));
				answer.setFk_idno(c.getLong(idxFkIdNo));
				
			} while (c.moveToNext());
		}
		return estrutura;
	}
	
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}
}