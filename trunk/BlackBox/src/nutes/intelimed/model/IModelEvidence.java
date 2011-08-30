package nutes.intelimed.model;

import nutes.intelimed.model.entity.Evidence;

/**
 * Interface de Evid�ncia
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelEvidence {

	public abstract long insertEvidence(Evidence evidence);
	public abstract boolean deleteEvidence();
	public abstract void fechar();

}
