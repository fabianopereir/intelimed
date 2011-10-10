package nutes.intelimed.model.tree;

import nutes.intelimed.model.ScriptConstants;
import nutes.intelimed.model.DatabaseHelper;
import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.tree.Node.NodesTableConstants;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
* Classe respons�vel por realizar consultas no banco na tabela de n�s
* @author Jamilson Batista (jamilsonbatista@gmail.com)
* @author Dyego Carlos (dyego12345@gmail.com)
*/
public class NodeDao extends GenericDao implements IModelNodeDao{
	
	private DatabaseHelper dbHelper;
	
	public static final String NOME_TABELA = "no";

	public NodeDao(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, ScriptConstants.NOME_BANCO, ScriptConstants.VERSAO_BANCO,
				ScriptConstants.getScriptDatabaseCreate(), ScriptConstants.getScriptDatabaseDelete());
		//db = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Busca um n� na base de dados
	 * @param Long fk_idno (identificador do n�)
	 * @return Node 
	 */
	public Node searchNode(Long fk_idno) {
		db = dbHelper.getWritableDatabase();
		Node node = null;
		
		try {

			Cursor c = db.query(NOME_TABELA, NodesTableConstants.colunas, NodesTableConstants.IDNO + "=" + fk_idno + " AND " + NodesTableConstants.DIAGNOSTICO + "= 1", null, null, null, null);

			if (c.moveToNext()) {
				node = new Node();
				node.setIdno(c.getLong(0));
				node.setDescricaoNo(c.getString(1));
				node.setDiagnostico(c.getInt(2));
			} 
			c.close();
		} catch (SQLException e) {
			Log.e(CATEGORIA,"Erro ao buscar o n�: " + e.toString());
			return null;
		}finally{
			this.fechar();
		}
		return node;
	}
	
	/**
	 * Insere um n� na base de dados
	 * @param Node
	 * @return Long fk_idno (identificador do n�)
	 */
	public long insertNode(Node node) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NodesTableConstants.IDNO, node.getIdno());
		values.put(NodesTableConstants.DESCRICAO_NO, node.getDescricaoNo());
		values.put(NodesTableConstants.DIAGNOSTICO, node.getDiagnostico());
		long id = db.insert(NOME_TABELA, "", values);
        Log.i(CATEGORIA,"Insere n�???");
        this.fechar();
		return id;
	}
	
	/**
	 * Deleta conte�do da tabela n� na base de dados
	 * @return boolean - se deletar retorna true
	 */
	public boolean deleteNode() {
		db = dbHelper.getWritableDatabase();
		boolean aux = true;
		try{
			db.delete(NOME_TABELA, null, null);
		}catch (Exception e) {
            aux=false;
            Log.i("Exception excluir",e.getMessage().toString());
		}finally{
			this.fechar();
		}		
		return aux;
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
	
}
