package nutes.intelimed.model.tree;

import nutes.intelimed.model.ScriptConstants;
import nutes.intelimed.model.DatabaseHelper;
import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.tree.Answer.AnswersTableConstants;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
 * Classe responsável por realizar consultas em banco na tabela de repostas
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class AnswersDao extends GenericDao implements IModelAnswersDao {
	
	private DatabaseHelper dbHelper;
	
	public static final String NOME_TABELA = "resposta";

	public AnswersDao(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, ScriptConstants.NOME_BANCO, ScriptConstants.VERSAO_BANCO,
				ScriptConstants.getScriptDatabaseCreate(), ScriptConstants.getScriptDatabaseDelete());
		//db = dbHelper.getWritableDatabase();
	}
	
	/**
	  * Busca uma resposta na base de dados
	  * @param idResposta - identificador da resposta
	  * @return resposta
	  */
	public Answer searchAnswer(Long idResposta) {
		db = dbHelper.getWritableDatabase();
		Answer answer = null;
	    Integer code = Integer.valueOf(idResposta.toString());  
	  
		Cursor c = db.query(NOME_TABELA, AnswersTableConstants.colunas,  AnswersTableConstants.ID_RESPOSTA +  "='"+code+"'", null, null, null, null);
		
		try {
			if (c.getCount() > 0) {
				c.moveToNext();
				answer = new Answer(c.getLong(0), c.getLong(1), c.getString(3), c.getLong(2));
				//Log.i(CATEGORIA,"Erro"+c.getLong(2)+" "+c.getLong(0)+" "+c.getLong(1)+" "+c.getString(1));
			}
		} catch (SQLException e) {

			Log.e(CATEGORIA, "Erro ao buscar resposta: " + e.toString());
			return null;
		}finally{
			c.close();
			this.fechar();
		}
		return answer;
	}
	
	/**
	 * Insere nova resposta no banco
	 * @param Answer - resposta a ser inserida no banco
	 * @return id - identificador de evidência
	 */
	public long insertAnswer(Answer answer) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(AnswersTableConstants.ID_RESPOSTA, answer.getIdResposta());
		values.put(AnswersTableConstants.FK_IDNO, answer.getIdNo());
		
		values.put(AnswersTableConstants.DESCRICAO_RESPOSTA, answer.getDescricaoResposta());
		values.put(AnswersTableConstants.CODE_RESPOSTA, answer.getCodeResposta());
		
		long id = db.insert(NOME_TABELA, "", values);
		this.fechar();
		return id;
	}
	
	/**
	 * Deleta respostas na base de dados
	 * @return boolean - se deletar retorna true
	 */
	public boolean deleteAnswer() {
		db = dbHelper.getWritableDatabase();
		boolean aux = true;
		try{
			db.delete(NOME_TABELA, null, null);
		}catch (Exception e) {
            aux=false;
            Log.i("Exception excluir",e.getMessage().toString());
		}
		this.fechar();
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
