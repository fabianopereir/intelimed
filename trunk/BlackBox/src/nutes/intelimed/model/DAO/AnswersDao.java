package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.Answer;
import nutes.intelimed.model.entity.Answer.AnswersTable;
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
	@Override
	public Answer searchAnswer(Long idResposta) {

		Answer answer = null;
	 	Long n = idResposta;  
	    Integer code = Integer.valueOf(n.toString());  
	  
		Cursor c = db.query(NOME_TABELA, Answer.colunas,  AnswersTable.IDRESPOSTA +  "="+code, null, null, null, null);
		
		try {
			if (c.moveToNext()) {
				answer = new Answer();
				answer.setIdresposta(c.getLong(0));
				answer.setDescricao_resposta(c.getString(1));
				answer.setCodeResposta(c.getLong(1));
				answer.setFk_idno(c.getLong(2));
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
		values.put(AnswersTable.IDRESPOSTA, answer.getIdresposta());
		values.put(AnswersTable.FK_IDNO, answer.getFk_idno());
		
		values.put(AnswersTable.DESCRICAO_RESPOSTA, answer.getDescricao_resposta());
		values.put(AnswersTable.CODERESPOSTA, answer.getCodeResposta());
		
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
	
	@Override
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
