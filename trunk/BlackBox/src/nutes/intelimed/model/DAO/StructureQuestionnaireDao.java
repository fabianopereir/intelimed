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
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description Classe responsável por consultas no banco
 */
public class StructureQuestionnaireDao implements IModelStructureQuestionnaire{
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";
	
	
	public static final String NOME_TABELA = " no"+" INNER JOIN resposta";
	
	protected SQLiteDatabase db;
	
	public StructureQuestionnaireDao()
	{
		
	}
	public StructureQuestionnaireDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public Cursor getCursor() {
		try {
			/*Cursor cursor = db.query(NOME_TABELA, StructureQuestionnaire.colunas, null, null, null,
					null, null, null);*/
			Cursor cursor = db.query(NOME_TABELA, StructureQuestionnaire.colunas, StructureQuestionnaireAll.FK_IDNO + "=" + StructureQuestionnaireAll.IDNO, null, null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	@Override
	public List<StructureQuestionnaire> listarEstruturaQuestionario() {
		Cursor c = getCursor();
		//Cursor c = db.query(NOME_TABELA, StructureQuestionnaire.colunas, StructureQuestionnaireAll.FK_IDNO + "=" + StructureQuestionnaireAll.IDNO, null, null, null, null);
		List<StructureQuestionnaire> estrutura = new ArrayList<StructureQuestionnaire>();
		
		int idxIdNo = c.getColumnIndex(StructureQuestionnaireAll.IDNO);
		int idxDescricao_no = c.getColumnIndex(StructureQuestionnaireAll.DESCRICAO_NO);
		int idxIdReposta = c.getColumnIndex(StructureQuestionnaireAll.IDRESPOSTA);
		int idxDescricao_reposta = c.getColumnIndex(StructureQuestionnaireAll.DESCRICAO_RESPOSTA);
		int idxFkIdNo = c.getColumnIndex(StructureQuestionnaireAll.FK_IDNO);
		
		
		if (c.moveToFirst()) {
			do {
				StructureQuestionnaire sqt = new StructureQuestionnaire();
				estrutura.add(sqt);

				sqt.idno = c.getLong(idxIdNo);
				sqt.descricao_no = c.getString(idxDescricao_no);
				sqt.idresposta = c.getInt(idxIdReposta);
				sqt.descricao_resposta = c.getString(idxDescricao_reposta);
				sqt.fk_idno = c.getInt(idxFkIdNo);
				
			} while (c.moveToNext());
		}
		return estrutura;
	}
	
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
