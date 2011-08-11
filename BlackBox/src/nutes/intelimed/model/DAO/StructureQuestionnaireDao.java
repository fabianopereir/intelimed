package nutes.intelimed.model.DAO;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.model.IModelStructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaire.StructureQuestionnaireAll;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

/**
 * Classe responsável por realizar consultas no banco
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class StructureQuestionnaireDao implements IModelStructureQuestionnaire{
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";
		
	public static final String NOME_TABELA = " no"+" INNER JOIN resposta";
	
	protected SQLiteDatabase db;
	
	public StructureQuestionnaireDao()	{}
	
	public StructureQuestionnaireDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	/**
	  * Método responsável pela captura do cursor
	  * @author Jamilson Batista (jamilsonbatista@gmail.com)
	  * @author Dyego Carlos (dyego12345@gmail.com)
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
	 *  Lista toda a estrutura do questionário (perguntas e alternativas)
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
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
	
	/**
	 * Busca utilizando as configurações definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider da estrutura do questionário
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param queryBuilder
	 * @param projection - condição de projeção
	 * @param selection - condição de seleção
	 * @param selectionArgs - argumentos da seleção
	 * @param groupBy - condição de agrupamento
	 * @param having - condição
	 * @param orderBy - condição de ordenamento
	 * @return Cursor - cursor com o retorno da consulta desejada
	 */
	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		Cursor c = queryBuilder.query(this.db, projection, selection,
				selectionArgs, groupBy, having, orderBy);
		return c;
	}
	
	@Override
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
