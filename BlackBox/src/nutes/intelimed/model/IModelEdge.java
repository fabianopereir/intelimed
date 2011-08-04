package nutes.intelimed.model;

import nutes.intelimed.model.entity.Edge;

public interface IModelEdge {
	public abstract Edge searchEdge(Long fk_idno, Long fk_idresposta);
	public abstract void fechar();

}
