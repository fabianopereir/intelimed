package nutes.intelimed.model.DAO;

import java.util.ArrayList;
import java.util.List;


import nutes.intelimed.model.InterfaceModelStructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaire.Perguntas;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class StructureQuestionnaireDao implements InterfaceModelStructureQuestionnaire{
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";
	public static final String NOME_TABELA = "questao";
	
	protected SQLiteDatabase db;
	
	public StructureQuestionnaireDao()
	{
		
	}
	public StructureQuestionnaireDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public Cursor getCursor() {
		try {
			Log.i("jamilson", "dentro do metodo getCursor");
			Cursor cursor = db.query(NOME_TABELA, StructureQuestionnaire.colunas, null, null, null,
					null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar as pacientes: " + e.toString());
			return null;
		}
	}
	
	@Override
	public List<StructureQuestionnaire> listarPerguntas() {
		Log.i("jamilson", "dentro do metodo listarPacientes");
		Cursor c = getCursor();
		List<StructureQuestionnaire> perguntas = new ArrayList<StructureQuestionnaire>();
		
		// Recupera os índices das colunas
		int idxId = c.getColumnIndex(Perguntas.IDPERGUNTA);
		int idxPergunta = c.getColumnIndex(Perguntas.PERGUNTA);
		int idxMetrica = c.getColumnIndex(Perguntas.IDMETRICA);

		if (c.moveToFirst()) {
			// Loop até o final
			do {
				StructureQuestionnaire sqt = new StructureQuestionnaire();
				perguntas.add(sqt);

				sqt.id = c.getLong(idxId);
				sqt.pergunta = c.getString(idxPergunta);
				sqt.idmetrica = c.getInt(idxMetrica);
				
			} while (c.moveToNext());
		}
		return perguntas;
	}
	
	/**
	 * Busca um paciente utilizando as configurações definidas no
	 * SQLiteQueryBuilder Utilizado pelo Content Provider de paciente
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
