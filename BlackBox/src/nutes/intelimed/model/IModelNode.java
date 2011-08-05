package nutes.intelimed.model;

import nutes.intelimed.model.entity.Node;

public interface IModelNode {
	public abstract Node searchNode(Long fk_idno);
	public abstract void fechar();
}
