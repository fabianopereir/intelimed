package nutes.intelimed.model;

import java.util.List;

import nutes.intelimed.model.entity.StructureQuestionnaire;

public interface InterfaceStructureQuestionnaire {
	public abstract List<StructureQuestionnaire> listarPerguntas();
	public abstract void fechar();
}
