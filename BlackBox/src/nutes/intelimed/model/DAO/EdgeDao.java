package nutes.intelimed.model.DAO;

import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import nutes.intelimed.model.IModelEdge;
import nutes.intelimed.model.entity.Edge;
import nutes.intelimed.model.entity.Edge.EdgeTable;

/**
 * Classe respons�vel por realizar consultas em banco na tabela de arestas
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EdgeDao implements IModelEdge {
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";

	public static final String NOME_TABELA = "aresta";

	protected SQLiteDatabase db;

	public EdgeDao() {}

	public EdgeDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	/**
	  * M�todo respons�vel pela captura do cursor
	  * @author Jamilson Batista (jamilsonbatista@gmail.com)
	  * @author Dyego Carlos (dyego12345@gmail.com)
	  * @return Cursor - cursor para consulta ao banco de dados
	  */
	public Cursor getCursor() {
		try {
			Cursor cursor = db.query(NOME_TABELA, Edge.colunas, null, null,
					null, null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	/**
	  * @author Jamilson Batista (jamilsonbatista@gmail.com)
	  * @author Dyego Carlos (dyego12345@gmail.com)
	  * Busca uma aresta na base de dados
	  * @param Long codeResposta (c�digo da resposta)
	  * @return edge 
	  */
	@Override
	public Edge searchEdge(Long codeResposta) {

		Edge edge = null;
	 	Long n = codeResposta;  
	    Integer n1 = Integer.valueOf(n.toString());  
	  
		Cursor c = db.query(NOME_TABELA, Edge.colunas,  EdgeTable.FK_IDRESPOSTA +  "="+n1, null, null, null, null);
		
		try {
			if (c.moveToNext()) {
				edge = new Edge();
				edge.setIdaresta(c.getLong(0));
				edge.setFk_idno(c.getLong(1));
				edge.setFk_idresposta(c.getLong(2));
			}
		} catch (SQLException e) {

			Log.e(CATEGORIA, "Erro ao buscar a aresta pelo c�digo da resposta: " + e.toString());
			return null;
		}

		return edge;
	}
	
	/**
	 * Busca utilizando as configura��es definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider de aresta
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
