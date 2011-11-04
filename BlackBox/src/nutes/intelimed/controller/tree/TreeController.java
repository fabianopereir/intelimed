package nutes.intelimed.controller.tree;

import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.AnswersDao;
import nutes.intelimed.model.tree.Edge;
import nutes.intelimed.model.tree.EdgeDao;
import nutes.intelimed.model.tree.IModelAnswersDao;
import nutes.intelimed.model.tree.IModelEdgeDao;
import nutes.intelimed.model.tree.IModelNodeDao;
import nutes.intelimed.model.tree.Node;
import nutes.intelimed.model.tree.NodeDao;
import android.content.Context;
import android.util.Log;

public class TreeController implements ITree {
	protected static final String CATEGORIA = "nutes";

	private IModelEdgeDao edgeDao;
	private IModelNodeDao nodeDao;
	private IModelAnswersDao answersDao;
	private Context ctx;

	/**
	 * Método construtor
	 * 
	 * @param contexto o qual será utilizado o TreeUpdate
	 */
	public TreeController(Context context) {
		this.ctx = context;
		edgeDao = (IModelEdgeDao) new EdgeDao(ctx);
		answersDao = (IModelAnswersDao) new AnswersDao(ctx);
		nodeDao = (IModelNodeDao) new NodeDao(ctx);
	}
	
	public TreeController(IModelEdgeDao edgeDao, IModelNodeDao nodeDao,
			IModelAnswersDao answersDao, Context ctx) {
		super();
		this.edgeDao = edgeDao;
		this.nodeDao = nodeDao;
		this.answersDao = answersDao;
		this.ctx = ctx;
	}

	public void deleteEdges() {
		edgeDao.deleteEdge();
	}

	public void deleteAnswers() {
		answersDao.deleteAnswer();
	}

	public void deleteNodes() {
		nodeDao.deleteNode();
	}

	public Long insertNode(Node node) {
		Log.i(CATEGORIA, "dentro do insertNode");
		return nodeDao.insertNode(node);
	}

	public Long insertNodeAnswers(Answer answer) {
		Log.i(CATEGORIA, "dentro do insertresposta");
		return answersDao.insertAnswer(answer);
	}

	public Long insertNodeEdge(Edge edge) {
		Log.i(CATEGORIA, "dentro do insertAresta");
		return edgeDao.insertEdge(edge);
	}

	public void receiveTree() throws Exception {
		TreeUpdate treeUpdate = new TreeUpdate(this);
		treeUpdate.run();
	}
	
}
