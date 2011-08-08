package nutes.intelimed.model;

import nutes.intelimed.model.entity.Evidence;

public interface IModelEvidence {

	public abstract long insertEvidence(Evidence evidence);
	public abstract void fechar();

}
