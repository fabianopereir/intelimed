package nutes.intelimed.model.tree;


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
}
