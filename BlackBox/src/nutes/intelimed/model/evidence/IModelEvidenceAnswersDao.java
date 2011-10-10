package nutes.intelimed.model.evidence;


/**
 * Interface de Respostas de Evidência
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelEvidenceAnswersDao {
	public abstract long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers);
	public abstract boolean deleteEvidenceAnswers();

}
