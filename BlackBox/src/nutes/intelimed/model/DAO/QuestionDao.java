package nutes.intelimed.model.DAO;

import java.util.ArrayList;
import java.util.List;


import nutes.intelimed.model.InterfaceModelQuestion;
import nutes.intelimed.model.entity.Question;
import nutes.intelimed.model.entity.Question.Perguntas;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class QuestionDao implements InterfaceModelQuestion{
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";
	public static final String NOME_TABELA = "questao";
	
	protected SQLiteDatabase db;
	
	public QuestionDao()
	{
		
	}
	public QuestionDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public Cursor getCursor() {
		try {
			Log.i("jamilson", "dentro do metodo getCursor");
			Cursor cursor = db.query(NOME_TABELA, Question.colunas, null, null, null,
					null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar as pacientes: " + e.toString());
			return null;
		}
	}
	
	@Override
	public List<Question> listarPerguntas() {
		Log.i("jamilson", "dentro do metodo listarPerguntas");
		Cursor c = getCursor();
		List<Question> perguntas = new ArrayList<Question>();
		
		int idxId = c.getColumnIndex(Perguntas.IDPERGUNTA);
		int idxPergunta = c.getColumnIndex(Perguntas.PERGUNTA);
		int idxMetrica = c.getColumnIndex(Perguntas.IDMETRICA);

		if (c.moveToFirst()) {
			do {
				Question sqt = new Question();
				perguntas.add(sqt);

				sqt.id = c.getLong(idxId);
				sqt.pergunta = c.getString(idxPergunta);
				sqt.idmetrica = c.getInt(idxMetrica);
				
			} while (c.moveToNext());
		}
		return perguntas;
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
