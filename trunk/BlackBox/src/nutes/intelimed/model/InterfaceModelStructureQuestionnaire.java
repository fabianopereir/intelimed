package nutes.intelimed.model;

import java.util.List;

import nutes.intelimed.model.entity.StructureQuestionnaireTest;

public interface InterfaceModelStructureQuestionnaire {
	public abstract List<StructureQuestionnaireTest> listarEstruturaQuestionario();
	public abstract void fechar();
}
