package nutes.intelimed.model.DAO;



import nutes.intelimed.model.IModelEvidenceAnswers;
import nutes.intelimed.model.entity.EvidenceAnswers;
import nutes.intelimed.model.entity.EvidenceAnswers.EvidenceAnswersTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;


/**
 * Classe responsável por realizar consultas em banco na tabela de evidências
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceAnswersDao implements IModelEvidenceAnswers {
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";

	public static final String NOME_TABELA = "evidencia_respostas";

	protected SQLiteDatabase db;

	public EvidenceAnswersDao() {}

	public EvidenceAnswersDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	/**
	 * Insere nova evidência no banco
	 * @param EvidenceAnswers - respostas da evidência a ser inserida no banco
	 * @return id - identificador de evidência
	 */
	public long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers) {
		ContentValues values = new ContentValues();
		values.put(EvidenceAnswersTable.FK_IDEVIDENCIA, evidenceAnswers.getFk_idevidencia());
		values.put(EvidenceAnswersTable.FK_IDNO, evidenceAnswers.getFk_idno());
		values.put(EvidenceAnswersTable.FK_IDRESPOSTA, evidenceAnswers.getFk_idResposta());
		
		
		long id = db.insert(NOME_TABELA, "", values);
		return id;
	}
	
	/**
	  * Método responsável pela captura do cursor 
	  * @return Cursor - cursor para consulta ao banco de dados
	  */
	public Cursor getCursor() {
		try {
			Cursor cursor = db.query(NOME_TABELA, EvidenceAnswers.colunas, null, null,
					null, null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	/**
	 * Busca utilizando as configurações definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider de respostas de evidência
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
