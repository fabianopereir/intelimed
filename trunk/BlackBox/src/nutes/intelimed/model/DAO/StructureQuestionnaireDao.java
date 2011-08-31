package nutes.intelimed.model.DAO;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaire.StructureQuestionnaireAll;
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
			Cursor cursor = db.query(NOME_TABELA, StructureQuestionnaire.colunas, StructureQuestionnaireAll.FK_IDNO + "=" + StructureQuestionnaireAll.IDNO, null, null, null, null);
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
		
		int idxIdNo = c.getColumnIndex(StructureQuestionnaireAll.IDNO);
		int idxDescricao_no = c.getColumnIndex(StructureQuestionnaireAll.DESCRICAO_NO);
		int idxIdReposta = c.getColumnIndex(StructureQuestionnaireAll.IDRESPOSTA);
		int idxCodeReposta = c.getColumnIndex(StructureQuestionnaireAll.CODERESPOSTA);
		int idxDescricao_reposta = c.getColumnIndex(StructureQuestionnaireAll.DESCRICAO_RESPOSTA);
		int idxFkIdNo = c.getColumnIndex(StructureQuestionnaireAll.FK_IDNO);
		
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
		return estrutura;
	}
	
	@Override
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
