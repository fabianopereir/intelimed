package nutes.intelimed.model;

import java.util.List;

import nutes.intelimed.model.entity.StructureQuestionnaire;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Interface de estrutura questionário
 */
public interface IModelStructureQuestionnaire {
	public abstract List<StructureQuestionnaire> listarEstruturaQuestionario();
	public abstract void fechar();
}
