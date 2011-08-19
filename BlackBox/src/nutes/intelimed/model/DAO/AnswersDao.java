package nutes.intelimed.model.DAO;

import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.util.Log;
import nutes.intelimed.model.IModelAnswers;
import nutes.intelimed.model.entity.Answer;
import nutes.intelimed.model.entity.Answer.AnswersTable;

/**
 * Classe responsável por realizar consultas em banco na tabela de repostas
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class AnswersDao extends GenericDao implements IModelAnswers {
	
	public static final String NOME_TABELA = "aresta";

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

		return answer;
	}
	
	@Override
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
