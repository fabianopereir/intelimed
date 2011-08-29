package nutes.intelimed.model;

import nutes.intelimed.model.entity.EvidenceAnswers;

/**
 * Interface de Respostas de EvidÍncia
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelEvidenceAnswers {
	public abstract long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers);
	public abstract int deleteEvidenceAnswers(long id);
	public abstract void fechar();

}
