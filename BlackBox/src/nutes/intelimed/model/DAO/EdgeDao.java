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
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Classe responsável por realizar consultas em banco de arestas
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
	 * @Description Captura cursor
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
	 * @Description Busca uma aresta na base de dados
	 * @param Long codeResposta (código da resposta)
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

			Log.e(CATEGORIA, "Erro ao buscar a aresta pelo código da resposta: " + e.toString());
			return null;
		}

		return edge;
	}
	
	/**
	 *  @Description Busca utilizando as configurações definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider da estrutura do questionário
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
