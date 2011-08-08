package nutes.intelimed.model.DAO;

import nutes.intelimed.model.IModelEvidence;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.Evidence.EvidenceTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;


/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Classe respons�vel por realizar consultas em banco na tabela de evid�ncias
 */
public class EvidenceDao implements IModelEvidence {
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";

	public static final String NOME_TABELA = "evidencia";

	protected SQLiteDatabase db;

	public EvidenceDao() {}

	public EvidenceDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	/**
	 * @Description Insere nova evid�ncia no banco
	 * @return id da evid�ncia
	 */
	public long insertEvidence(Evidence evidence) {
		ContentValues values = new ContentValues();
		values.put(EvidenceTable.JUSTIFICATIVA, evidence.getJustificativa());
		values.put(EvidenceTable.MEDICO, evidence.getMedico());
		values.put(EvidenceTable.SISTEMA, evidence.getSistema());
		
		long id = db.insert(NOME_TABELA, "", values);
		return id;
	}
	
	/**
	 * @Description Captura cursor
	 * @return Cursor - cursor para consulta ao banco de dados
	 */
	public Cursor getCursor() {
		try {
			Cursor cursor = db.query(NOME_TABELA, Evidence.colunas, null, null,
					null, null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	/**
	 *  @Description Busca utilizando as configura��es definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider da estrutura do question�rio
	 *  @param queryBuilder
	 *  @param projection
	 *  @param selection
	 *  @param selectionArgs
	 *  @param groupBy
	 *  @param having
	 *  @param orderBy
	 *  @return Cursor - cursor com o retorno da consulta desejada
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
