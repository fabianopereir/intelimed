package nutes.intelimed.model.DAO;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.model.InterfaceModelStructureQuestionnaire_;
import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaire.StructureQuestionnaireQuestion;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class StructureQuestionnaireDao implements InterfaceModelStructureQuestionnaire_{
	
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";
	public static final String NOME_TABELA = "estrutura_questionario INNER JOIN"+
											" questao ON (estrutura_questionario.fk_idquestao = questao.idquestao)";
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
	public List<StructureQuestionnaire> listarEstruturaQuestionario() {
		Log.i("jamilson", "dentro do metodo listarEstruturaQuestionario");
		Cursor c = getCursor();
		List<StructureQuestionnaire> estrutura = new ArrayList<StructureQuestionnaire>();
		
		int idxIdEstruturaQuestionario = c.getColumnIndex(StructureQuestionnaireQuestion.IDESTRUTURA_QUESTIONARIO);
		int idxOrdem = c.getColumnIndex(StructureQuestionnaireQuestion.ORDDEM);
		int idxFkIdDiagnostico = c.getColumnIndex(StructureQuestionnaireQuestion.FK_IDDIAGNOSTICO);
		int idxFkIdPergunta = c.getColumnIndex(StructureQuestionnaireQuestion.FK_IDPERGUNTA);
		int idxPergunta = c.getColumnIndex(StructureQuestionnaireQuestion.PERGUNTA);
		int idxFkIdMetrica = c.getColumnIndex(StructureQuestionnaireQuestion.FK_IDMETRICA);
		
		if (c.moveToFirst()) {
			do {
				StructureQuestionnaire sqt = new StructureQuestionnaire();
				estrutura.add(sqt);

				sqt.idestrutura_questionario = c.getLong(idxIdEstruturaQuestionario);
				sqt.ordem = c.getInt(idxOrdem);
				sqt.fk_iddiagnostico = c.getInt(idxFkIdDiagnostico);
				sqt.fk_idquestao = c.getInt(idxFkIdPergunta);
				sqt.pergunta = c.getString(idxPergunta);
				sqt.fk_idmetrica = c.getInt(idxFkIdMetrica);
				
			} while (c.moveToNext());
		}
		return estrutura;
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
