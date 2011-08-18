package nutes.intelimed.model.DAO;

import nutes.intelimed.model.IModelEvidenceToServer;
import nutes.intelimed.model.entity.EvidenceToServer;
import nutes.intelimed.model.entity.EvidenceToServer.EvidenceToServerTable;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class EvidenceToServerDao extends GenericDao implements IModelEvidenceToServer{
	public static final String NOME_TABELA = "evidencia_respostas INNER JOIN resposta"+ 
	"INNER JOIN evidencia ON idevidencia = fk_idevidencia";

	public EvidenceToServerDao() {}

	public EvidenceToServerDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	/**
	 * Busca respostas e justificativa do médico sobre o diagnóstico na base de dados para envio ao servidor
	 * @param Long fk_idevidencia (identificador da evidência de um formulário)
	 * @return EvidenceToServer
	 */
	public EvidenceToServer searchEvidenceToServer(){

		EvidenceToServer evidence = null;
		
		try {

			Cursor c = db.query(NOME_TABELA, EvidenceToServer.colunas, EvidenceToServerTable.FK_IDRESPOSTA + "=" + EvidenceToServerTable.IDRESPOSTA + " AND " + EvidenceToServerTable.IDEVIDENCIA + "=" + EvidenceToServerTable.FK_IDEVIDENCIA, null, null, null, null);

			if (c.moveToNext()) {

				evidence = new EvidenceToServer();
				evidence.setIdevidencia(c.getLong(0));
				evidence.setSistema(c.getString(1));
				evidence.setMedico(c.getString(2));
				evidence.setJustificativa(c.getString(3));
				evidence.setIdevidencia_respostas(c.getLong(4));
				evidence.setFk_idno(c.getLong(5));
				evidence.setIdresposta(c.getLong(6));
				evidence.setFk_idresposta(c.getLong(7));
				evidence.setFk_idevidencia(c.getLong(8));
			} else{
				return null;
			}
		} catch (SQLException e) {
			Log.e(CATEGORIA,"Erro ao buscar evidência: " + e.toString());
			return null;
		}

		return evidence;
	}
	
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}
}