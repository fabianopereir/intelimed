package nutes.intelimed.controller.tree;

import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.Edge;
import nutes.intelimed.model.tree.Node;

public interface ITree {
	
	public Long insertNode(Node node);
	
	public Long insertNodeAnswers(Answer answer);
	
	public Long insertNodeEdge(Edge edge);
	
	public void deleteEdges();
	
	public void deleteAnswers();
	
	public void deleteNodes();
	
	public void receiveTree() throws Exception;
}
