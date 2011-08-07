package nutes.intelimed.model;

import nutes.intelimed.model.entity.Edge;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Interface de ARESTA
 */

public interface IModelEdge {
	public abstract Edge searchEdge(Long fk_idresposta);
	public abstract void fechar();
}
