package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.Answer;
import nutes.intelimed.model.entity.Answer.AnswersTableConstants;
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
	
	public static final String NOME_TABELA = "resposta";

	public AnswersDao() {}

	public AnswersDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	/**
	  * Busca uma resposta na base de dados
	  * @param idResposta - identificador da resposta
	  * @return resposta
	  */
	public Answer searchAnswer(Long idResposta) {

		Answer answer = null;
	 	Long n = idResposta;  
	    Integer code = Integer.valueOf(n.toString());  
	  
		Cursor c = db.query(NOME_TABELA, AnswersTableConstants.colunas,  AnswersTableConstants.ID_RESPOSTA +  "="+code, null, null, null, null);
		
		try {
			if (c.moveToNext()) {
				answer = new Answer(c.getLong(2), c.getLong(0), c.getString(1), c.getLong(1));
			}
		} catch (SQLException e) {

			Log.e(CATEGORIA, "Erro ao buscar resposta: " + e.toString());
			return null;
		}
		c.close();
		return answer;
	}
	
	/**
	 * Insere nova resposta no banco
	 * @param Answer - resposta a ser inserida no banco
	 * @return id - identificador de evidência
	 */
	public long insertAnswer(Answer answer) {
		ContentValues values = new ContentValues();
		values.put(AnswersTableConstants.ID_RESPOSTA, answer.getIdResposta());
		values.put(AnswersTableConstants.FK_IDNO, answer.getIdNo());
		
		values.put(AnswersTableConstants.DESCRICAO_RESPOSTA, answer.getDescricaoResposta());
		values.put(AnswersTableConstants.CODE_RESPOSTA, answer.getCodeResposta());
		
		long id = db.insert(NOME_TABELA, "", values);
		return id;
	}
	
	/**
	 * Deleta respostas na base de dados
	 * @return boolean - se deletar retorna true
	 */
	public boolean deleteAnswer() {
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
	}

}
