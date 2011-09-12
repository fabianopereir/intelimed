package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.Node;
import nutes.intelimed.model.entity.Node.NodeTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
* Classe responsável por realizar consultas no banco na tabela de nós
* @author Jamilson Batista (jamilsonbatista@gmail.com)
* @author Dyego Carlos (dyego12345@gmail.com)
*/
public class NodeDao extends GenericDao implements IModelNodeDao{
	
	public static final String NOME_TABELA = "no";

	public NodeDao() {}

	public NodeDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	/**
	 * Busca um nó na base de dados
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
	 * Insere um nó na base de dados
	 * @param Node
	 * @return Long fk_idno (identificador do nó)
	 */
	public long insertNode(Node node) {
		ContentValues values = new ContentValues();

		values.put(NodeTable.DESCRICAO_NO, node.getDescricaoNo());
		values.put(NodeTable.DIAGNOSTICO, node.getDiagnostico());
		long id = db.insert(NOME_TABELA, "", values);
        Log.i(CATEGORIA,"Insere nó???");
		return id;
	}
	
	/**
	 * Deleta conteúdo da tabela nó na base de dados
	 * @return boolean - se deletar retorna true
	 */
	public boolean deleteNode() {
		boolean aux = true;
		try{
			db.delete(NOME_TABELA, null, null);
		}catch (Exception e) {
            aux=false;
            Log.i("Exception excluir",e.getMessage().toString());
		}
		
		return aux;
	}
	
	
	
	@Override
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}
	
}
