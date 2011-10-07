package nutes.intelimed.model.diagnostic;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.diagnostic.StructureQuestionnaire.StructureQuestionnaireTableConstants;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
 * Classe responsável por realizar consultas no banco
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class StructureQuestionnaireDao extends GenericDao implements IModelStructureQuestionnaireDao{
		
	public static final String NOME_TABELA = " no"+" INNER JOIN resposta";
	
	public StructureQuestionnaireDao()	{}
	
	public StructureQuestionnaireDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	/**
	  * Método responsável pela captura do cursor
	  * @return Cursor - cursor para consulta ao banco de dados
	  */
	public Cursor getCursor() {
		try {
			Cursor cursor = db.query(NOME_TABELA, StructureQuestionnaireTableConstants.colunas, StructureQuestionnaireTableConstants.FK_IDNO + "=" + StructureQuestionnaireTableConstants.IDNO, null, null, null, null);
			return cursor;

		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	/**
	 * Lista toda a estrutura do questionário (perguntas e alternativas)
	 * @return List<StructureQuestionnaire> 
	 */
	public List<StructureQuestionnaire> listarEstruturaQuestionario() {

		Cursor c = getCursor();
		
		List<StructureQuestionnaire> estrutura = new ArrayList<StructureQuestionnaire>();
		
		int idxIdNo = c.getColumnIndex(StructureQuestionnaireTableConstants.IDNO);
		int idxDescricao_no = c.getColumnIndex(StructureQuestionnaireTableConstants.DESCRICAO_NO);
		int idxIdReposta = c.getColumnIndex(StructureQuestionnaireTableConstants.IDRESPOSTA);
		int idxCodeReposta = c.getColumnIndex(StructureQuestionnaireTableConstants.CODERESPOSTA);
		int idxDescricao_reposta = c.getColumnIndex(StructureQuestionnaireTableConstants.DESCRICAO_RESPOSTA);
		int idxFkIdNo = c.getColumnIndex(StructureQuestionnaireTableConstants.FK_IDNO);
		
		if (c.moveToFirst()) {
			do {
				StructureQuestionnaire sqt = new StructureQuestionnaire();
				estrutura.add(sqt);

				sqt.setIdno(c.getLong(idxIdNo));
				sqt.setDescricao_no(c.getString(idxDescricao_no));
				sqt.setIdresposta(c.getInt(idxIdReposta));
				sqt.setCodeResposta(c.getInt(idxCodeReposta));
				sqt.setDescricao_resposta(c.getString(idxDescricao_reposta));
				sqt.setFk_idno(c.getInt(idxFkIdNo));
				
			} while (c.moveToNext());
		}
		c.close();
		return estrutura;
	}
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
