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
	public Edge searchEdge(Long fk_idno, Long fk_idresposta) {

		Edge edge = null;
		
		try {
			Log.i("jamilson", "dentro do método buscarPacientePorNome");
			Cursor c = db.query(NOME_TABELA, Edge.colunas, EdgeTable.FK_IDRESPOSTA
					+ "='" + fk_idresposta + "'", null, null, null, null);

			if (c.moveToNext()) {

				edge = new Edge();
				edge.idaresta = c.getLong(0);
				edge.fk_idno = c.getLong(1);
				edge.fk_idresposta = c.getLong(2);
			}
			// c.close();
		} catch (SQLException e) {
			Log.e(CATEGORIA,
					"Erro ao buscar a paciente pelo nome: " + e.toString());
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
