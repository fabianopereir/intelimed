package nutes.intelimed.controller.tree;

import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.Edge;
import nutes.intelimed.model.tree.Node;

public interface ITree {
	
	public void insertNode(Node node);
	
	public void insertNodeAnswers(Answer answer);
	
	public void insertNodeEdge(Edge edge);
	
	public void deleteEdges();
	
	public void deleteAnswers();
	
	public void deleteNodes();
	
	public void receiveTree() throws Exception;
}
