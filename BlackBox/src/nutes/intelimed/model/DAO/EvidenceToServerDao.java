package nutes.intelimed.model.DAO;

import java.util.ArrayList;

import nutes.intelimed.model.entity.EvidenceToServer;
import nutes.intelimed.model.entity.EvidenceToServer.EvidenceToServerTable;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
 * Classe responsável por realizar manipulação em banco para preparar evidência para envio
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceToServerDao extends GenericDao implements IModelEvidenceToServerDao{
	public static final String NOME_TABELA = "evidencia_respostas INNER JOIN resposta"+" INNER JOIN evidencia";

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

			Cursor cursor = db.query(NOME_TABELA, EvidenceToServer.colunas, EvidenceToServerTable.FK_IDRESPOSTA + "=" + EvidenceToServerTable.IDRESPOSTA + " AND " + EvidenceToServerTable.IDEVIDENCIA + "=" + EvidenceToServerTable.FK_IDEVIDENCIA, null, null, null, null);
			return cursor;

		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	/**
	 * Busca evidência e suas respostas com justificativa do médico sobre o diagnóstico na base de dados
	 * @return ArrayList<Object>
	 */
	public ArrayList<EvidenceToServer> searchEvidenceToServer(){

		Cursor c = getCursor();
		
		ArrayList<EvidenceToServer> estrutura = new ArrayList<EvidenceToServer>();
		
		int idxIdevidencia_respostas = c.getColumnIndex(EvidenceToServerTable.IDEVIDENCIA_RESPOSTAS);
		int idxFkIdNo = c.getColumnIndex(EvidenceToServerTable.FK_IDNO);
		int idxIdresposta = c.getColumnIndex(EvidenceToServerTable.IDRESPOSTA);
		int idxIdevidencia = c.getColumnIndex(EvidenceToServerTable.IDEVIDENCIA);
		int idxSistema = c.getColumnIndex(EvidenceToServerTable.SISTEMA);
		int idxMedico = c.getColumnIndex(EvidenceToServerTable.MEDICO);
		int idxJustificativa = c.getColumnIndex(EvidenceToServerTable.JUSTIFICATIVA);
		
		
		if (c.moveToFirst()) {
			do {
				
				EvidenceToServer toServer = new EvidenceToServer();
				estrutura.add(toServer);
				toServer.setIdevidencia_respostas(c.getLong(idxIdevidencia_respostas));
				toServer.setFk_idno(c.getLong(idxFkIdNo));
				toServer.setIdresposta(c.getLong(idxIdresposta));
				toServer.setIdevidencia(c.getLong(idxIdevidencia));
				toServer.setSistema(c.getString(idxSistema));
				toServer.setMedico(c.getString(idxMedico));
				toServer.setJustificativa(c.getString(idxJustificativa));
				
				
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