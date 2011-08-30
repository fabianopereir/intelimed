package nutes.intelimed.model;

import nutes.intelimed.model.entity.EvidenceAnswers;

/**
 * Interface de Respostas de Evidência
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelEvidenceAnswers {
	public abstract long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers);
	public abstract boolean deleteEvidenceAnswers();
	public abstract void fechar();

}
