package nutes.intelimed.model.DAO;

import java.util.List;

import nutes.intelimed.model.entity.StructureQuestionnaire;

/**
 * Interface de estrutura de questionário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelStructureQuestionnaireDao {
	public abstract List<StructureQuestionnaire> listarEstruturaQuestionario();
	public abstract void fechar();
}
