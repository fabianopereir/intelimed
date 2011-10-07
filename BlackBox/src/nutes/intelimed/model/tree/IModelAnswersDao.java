package nutes.intelimed.model.tree;


/**
 * Interface de Resposta
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)	
 * 
 */

public interface IModelAnswersDao {
	public abstract Answer searchAnswer(Long idresposta);
	public abstract long insertAnswer(Answer answer);
	public abstract boolean deleteAnswer();
	public abstract void fechar();
}
