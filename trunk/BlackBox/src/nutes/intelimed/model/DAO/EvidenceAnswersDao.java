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
 * Classe respons�vel por realizar consultas em banco na tabela de evid�ncias
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
	 * Insere nova evid�ncia no banco
	 * @param EvidenceAnswers - respostas da evid�ncia a ser inserida no banco
	 * @return id - identificador de evid�ncia
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
	  * M�todo respons�vel pela captura do cursor 
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
	 * Busca utilizando as configura��es definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider de respostas de evid�ncia
	 * @param queryBuilder
	 * @param projection - condi��o de proje��o
	 * @param selection - condi��o de sele��o
	 * @param selectionArgs - argumentos da sele��o
	 * @param groupBy - condi��o de agrupamento
	 * @param having - condi��o
	 * @param orderBy - condi��o de ordenamento
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
