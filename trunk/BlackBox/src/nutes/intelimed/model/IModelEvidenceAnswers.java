package nutes.intelimed.model;

import nutes.intelimed.model.entity.EvidenceAnswers;

public interface IModelEvidenceAnswers {
	public abstract long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers);
	public abstract void fechar();

}
