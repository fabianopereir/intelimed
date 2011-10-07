package nutes.intelimed.model.tree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.util.Log;
import nutes.intelimed.model.BaseScript;
import nutes.intelimed.model.DatabaseHelper;
import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.tree.Edge.EdgesTableConstants;

/**
 * Classe responsável por realizar consultas em banco na tabela de arestas
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EdgeDao extends GenericDao implements IModelEdgeDao {
	
	private DatabaseHelper dbHelper;
	
	public static final String NOME_TABELA = "aresta";

	public EdgeDao() {}

	public EdgeDao(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, BaseScript.NOME_BANCO, BaseScript.VERSAO_BANCO,
				BaseScript.getScriptDatabaseCreate(), BaseScript.getScriptDatabaseDelete());
		db = dbHelper.getWritableDatabase();
	}
	
	/**
	  * Busca uma aresta na base de dados
	  * @param codeResposta - código da resposta
	  * @return aresta 
	  */
	public Edge searchEdge(Long codeResposta) {

		Edge edge = null;
	 	Long n = codeResposta;  
	    Integer code = Integer.valueOf(n.toString());  
	  
		Cursor c = db.query(NOME_TABELA, EdgesTableConstants.colunas,  EdgesTableConstants.FK_IDRESPOSTA +  "="+code, null, null, null, null);
		
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
		c.close();
		return edge;
	}
	
	/**
	 * Insere uma aresta na base de dados
	 * @param Edge
	 * @return Long id (identificador da aresta)
	 */
	public long insertEdge(Edge edge) {
		ContentValues values = new ContentValues();
		values.put(EdgesTableConstants.IDARESTA, edge.getIdaresta());
		values.put(EdgesTableConstants.FK_IDNO, edge.getFk_idno());
		values.put(EdgesTableConstants.FK_IDRESPOSTA, edge.getFk_idresposta());
		long id = db.insert(NOME_TABELA, "", values);
		return id;
	}
	
	/**
	 * Deleta conteúdo da tabela aresta na base de dados
	 * @return boolean - se deletar retorna true
	 */
	public boolean deleteEdge() {
		boolean aux = true;
		try{
			db.delete(NOME_TABELA, null, null);
		}catch (Exception e) {
            aux=false;
            Log.i("Exception excluir",e.getMessage().toString());
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
