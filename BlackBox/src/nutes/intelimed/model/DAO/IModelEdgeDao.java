package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.Edge;

/**
 * Interface de Aresta
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)	
 * 
 */

public interface IModelEdgeDao {
	public abstract Edge searchEdge(Long fk_idresposta);
	public abstract long insertEdge(Edge edge);
	public abstract boolean deleteEdge();
	public abstract void fechar();
}
