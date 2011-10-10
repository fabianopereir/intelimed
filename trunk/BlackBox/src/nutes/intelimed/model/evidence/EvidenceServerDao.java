package nutes.intelimed.model.evidence;

import java.util.ArrayList;

import nutes.intelimed.model.ScriptConstants;
import nutes.intelimed.model.DatabaseHelper;
import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.evidence.EvidenceServer.EvidenceServerTableConstants;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
 * Classe responsável por realizar manipulação em banco para preparar evidência para envio
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceServerDao extends GenericDao implements IModelEvidenceServerDao{
	
	private DatabaseHelper dbHelper;
	
	public static final String NOME_TABELA = "evidencia_respostas INNER JOIN resposta"+" INNER JOIN evidencia";

	public EvidenceServerDao(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, ScriptConstants.NOME_BANCO, ScriptConstants.VERSAO_BANCO,
				ScriptConstants.getScriptDatabaseCreate(), ScriptConstants.getScriptDatabaseDelete());
		//db = dbHelper.getWritableDatabase();
	}
	
	/**
	  * Método responsável pela captura do cursor
	  * @return Cursor - cursor para consulta ao banco de dados
	  */
	public Cursor getCursor() {
		try {

			Cursor cursor = db.query(NOME_TABELA, EvidenceServerTableConstants.colunas, EvidenceServerTableConstants.FK_IDRESPOSTA + "=" + EvidenceServerTableConstants.IDRESPOSTA + " AND " + EvidenceServerTableConstants.IDEVIDENCIA + "=" + EvidenceServerTableConstants.FK_IDEVIDENCIA, null, null, null, null);
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
	public ArrayList<EvidenceServer> searchEvidenceToServer(){
		db = dbHelper.getWritableDatabase();
		Cursor c = getCursor();
		
		ArrayList<EvidenceServer> estrutura = new ArrayList<EvidenceServer>();
		
		int idxIdevidencia_respostas = c.getColumnIndex(EvidenceServerTableConstants.IDEVIDENCIA_RESPOSTAS);
		int idxFkIdNo = c.getColumnIndex(EvidenceServerTableConstants.FK_IDNO);
		int idxIdresposta = c.getColumnIndex(EvidenceServerTableConstants.IDRESPOSTA);
		int idxIdevidencia = c.getColumnIndex(EvidenceServerTableConstants.IDEVIDENCIA);
		int idxSistema = c.getColumnIndex(EvidenceServerTableConstants.SISTEMA);
		int idxMedico = c.getColumnIndex(EvidenceServerTableConstants.MEDICO);
		int idxJustificativa = c.getColumnIndex(EvidenceServerTableConstants.JUSTIFICATIVA);
		
		
		if (c.moveToFirst()) {
			do {
				
				EvidenceServer toServer = new EvidenceServer();
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
		c.close();
		this.fechar();
		return estrutura;
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