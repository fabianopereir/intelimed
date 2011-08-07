package nutes.intelimed.model;

import nutes.intelimed.model.entity.Node;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Interface de NO
 */

public interface IModelNode {
	public abstract Node searchNode(Long fk_idno);
	public abstract void fechar();
}
