package nutes.intelimed.model;

import nutes.intelimed.model.entity.Answer;

/**
 * Interface de Resposta
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)	
 * 
 */

public interface IModelAnswers {
	public abstract Answer searchAnswer(Long idresposta);
	public abstract void fechar();
}