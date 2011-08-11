package nutes.intelimed.model;

import nutes.intelimed.model.entity.Edge;

/**
 * Interface de Aresta
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)	
 * 
 * 
 */

public interface IModelEdge {
	public abstract Edge searchEdge(Long fk_idresposta);
	public abstract void fechar();
}
