package nutes.intelimed.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Classe gen�rica de DAOs para inst�ncia do banco e cursor 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class GenericDao {
	
	protected SQLiteDatabase db;
	protected static final String CATEGORIA = "nutes";
	protected static final String NOME_BANCO = "caixapreta";
	protected static final int VERSAO_BANCO = 36;
	
	/**
	 * Busca utilizando as configura��es definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider
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
	
	public void fechar(){
		this.db.close();
	}
}
