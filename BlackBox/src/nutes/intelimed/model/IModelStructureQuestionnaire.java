package nutes.intelimed.model;

import java.util.List;

import nutes.intelimed.model.entity.StructureQuestionnaire;

public interface IModelStructureQuestionnaire {
	public abstract List<StructureQuestionnaire> listarEstruturaQuestionario();
	public abstract void fechar();
}
