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
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * Classe respons�vel por realizar consultas em banco na tabela de evid�ncias
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
	 * Insere nova evid�ncia no banco
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param Evidence - evid�ncia a ser inserida no banco
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
	 * Captura cursor
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
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
	 * Busca utilizando as configura��es definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider da evid�ncia
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
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
