package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.Evidence;

/**
 * Interface de Evidência
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelEvidenceDao {

	public abstract long insertEvidence(Evidence evidence);
	public abstract boolean deleteEvidence();
	public abstract void fechar();

}
