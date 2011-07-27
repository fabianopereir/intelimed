package nutes.intelimed.model.DAO;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.model.InterfaceModelStructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaireTest;
import nutes.intelimed.model.entity.StructureQuestionnaireTest.StructureQuestionnaireAll;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class StructureQuestionnaireDaoTest implements InterfaceModelStructureQuestionnaire{
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";
	
	
	public static final String NOME_TABELA = " estrutura_questionario as estrutura"+
		" INNER JOIN questao as quest ON  quest.idquestao = estrutura.fk_idquestao" +
		" INNER JOIN metrica as metric ON quest.fk_idmetrica = metric.idmetrica"+
		" INNER JOIN validacao_metrica as valmetic ON valmetic.fk_idmetrica = quest.fk_idmetrica" +
		" INNER JOIN padrao_validacao as pval ON valmetic.fk_idpadrao_validacao = pval.idpadrao_validacao";
		
		
	
	protected SQLiteDatabase db;
	
	public StructureQuestionnaireDaoTest()
	{
		
	}
	public StructureQuestionnaireDaoTest(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public Cursor getCursor() {
		try {
			Log.i("jamilson", "dentro do metodo getCursor");
			Cursor cursor = db.query(NOME_TABELA, StructureQuestionnaireTest.colunas, null, null, null,
					null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar as pacientes: " + e.toString());
			return null;
		}
	}
	@Override
	public List<StructureQuestionnaireTest> listarEstruturaQuestionario() {
		Log.i("jamilson", "dentro do metodo listarEstruturaQuestionario");
		Cursor c = getCursor();
		List<StructureQuestionnaireTest> estrutura = new ArrayList<StructureQuestionnaireTest>();
		
		int idxIdEstruturaQuestionario = c.getColumnIndex(StructureQuestionnaireAll.IDESTRUTURA_QUESTIONARIO);
		int idxFkIdPergunta = c.getColumnIndex(StructureQuestionnaireAll.FK_IDPERGUNTA);
		int idxPergunta = c.getColumnIndex(StructureQuestionnaireAll.PERGUNTA);
		int idxFkIdMetrica = c.getColumnIndex(StructureQuestionnaireAll.FK_IDMETRICA);
		int idxTipo = c.getColumnIndex(StructureQuestionnaireAll.TIPO);
		int idxFkIdPadraoValidacao = c.getColumnIndex(StructureQuestionnaireAll.FK_IDPADRAO_VALIDACAO);
		int idxDescricao = c.getColumnIndex(StructureQuestionnaireAll.DESCRICAO);		
		int idxOrdem = c.getColumnIndex(StructureQuestionnaireAll.ORDDEM);
		//int idxFkIdDiagnostico = c.getColumnIndex(StructureQuestionnaireAll.FK_IDDIAGNOSTICO);
		
		
		if (c.moveToFirst()) {
			do {
				StructureQuestionnaireTest sqt = new StructureQuestionnaireTest();
				estrutura.add(sqt);

				sqt.idestrutura_questionario = c.getLong(idxIdEstruturaQuestionario);
				sqt.fk_idquestao = c.getInt(idxFkIdPergunta);
				sqt.pergunta = c.getString(idxPergunta);
				sqt.fk_idmetrica = c.getInt(idxFkIdMetrica);
				sqt.tipo = c.getString(idxTipo);
				sqt.fk_idpadrao_validacao = c.getInt(idxFkIdPadraoValidacao);
				sqt.descricao = c.getString(idxDescricao);
				sqt.ordem = c.getInt(idxOrdem);
				//sqt.fk_iddiagnostico = c.getInt(idxFkIdDiagnostico);
				
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
