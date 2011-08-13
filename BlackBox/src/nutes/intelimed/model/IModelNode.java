package nutes.intelimed.model;

import nutes.intelimed.model.entity.Node;

/**
 * Interface de NÓ
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelNode {
	public abstract Node searchNode(Long fk_idno);
	public abstract void fechar();
}
