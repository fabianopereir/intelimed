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
* Classe respons�vel por realizar consultas no banco na tabela de n�s
* @author Jamilson Batista (jamilsonbatista@gmail.com)
* @author Dyego Carlos (dyego12345@gmail.com)
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
	  * M�todo respons�vel pela captura do cursor
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
	 * Busca um n� na base de dados
	 * @param Long fk_idno (identificador do n�)
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
			Log.e(CATEGORIA,"Erro ao buscar o n�: " + e.toString());
			return null;
		}

		return node;
	}
	
	/**
	 * Busca utilizando as configura��es definidas no SQLiteQueryBuilder
	 *    Utilizado pelo Content Provider de n�
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
