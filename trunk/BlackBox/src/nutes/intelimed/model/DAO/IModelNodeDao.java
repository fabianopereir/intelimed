package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.Node;

/**
 * Interface de NÓ
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelNodeDao {
	public abstract Node searchNode(Long fk_idno);
	public abstract long insertNode(Node node);
	public abstract boolean deleteNode();
	public abstract void fechar();
}
