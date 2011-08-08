package nutes.intelimed.model.DAO;

import nutes.intelimed.model.IModelNode;
import nutes.intelimed.model.entity.Node;
import nutes.intelimed.model.entity.Node.NodeTable;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

/**
* 
* @author Jamilson Batista e Dyego Carlos
* @Description Classe responsável por realizar consultas no banco na tabela de nós
*/
public class NodeDao implements IModelNode{
	private static final String CATEGORIA = "nutes";
	private static final String NOME_BANCO = "caixapreta";

	public static final String NOME_TABELA = "no";

	protected SQLiteDatabase db;

	public NodeDao() {}

	public NodeDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	/**
	 * @Description Captura cursor
	 * @return Cursor - cursor para consulta ao banco de dados
	 */
	public Cursor getCursor() {
		try {
			Cursor cursor = db.query(NOME_TABELA, Node.colunas, null, null,
					null, null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar: " + e.toString());
			return null;
		}
	}
	
	/**
	 * @Description Busca um nó na base de dados
	 * @param Long fk_idno (identificador do nó)
	 * @return Node 
	 */
	public Node searchNode(Long fk_idno) {

		Node node = null;
		
		try {

			Cursor c = db.query(NOME_TABELA, Node.colunas, NodeTable.IDNO + "=" + fk_idno + " AND " + NodeTable.DIAGNOSTICO + "= 1", null, null, null, null);

			if (c.moveToNext()) {

				node = new Node();
				node.setIdno(c.getLong(0));
				node.setDescricaoNo(c.getString(1));
				node.setDiagnostico(c.getInt(2));
			} else{
				return null;
			}
		} catch (SQLException e) {
			Log.e(CATEGORIA,"Erro ao buscar o nó: " + e.toString());
			return null;
		}

		return node;
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
