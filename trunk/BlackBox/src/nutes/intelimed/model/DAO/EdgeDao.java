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

	public EdgeDao() {
	}

	public EdgeDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

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

	@Override
	public Edge searchEdge(Long codeResposta) {

		Edge edge = null;
	 	Long n = codeResposta;  
	    Integer n1 = Integer.valueOf(n.toString());  
		    
		try {
			Log.i("jamilson", "dentro do método searchEdge "+n1);
			Cursor c = db.query(NOME_TABELA, Edge.colunas,  EdgeTable.FK_IDRESPOSTA +  "= 1", null, null, null, null);
			Log.i("jamilson","Passou1");

			


			if (c.moveToNext()) {
				Log.i("jamilson","Passou");
				edge = new Edge();
				edge.idaresta = c.getLong(0);
				edge.fk_idno = c.getLong(1);
				edge.fk_idresposta = c.getLong(2);
			}
			// c.close();
		} catch (SQLException e) {

			Log.e(CATEGORIA, "Erro ao buscar a aresta pelo código da resposta: " + e.toString());
			return null;
		}

		return edge;
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
