package nutes.intelimed.controller;

import nutes.intelimed.model.AnswerScript;
import nutes.intelimed.model.EdgeScript;
import nutes.intelimed.model.NodeScript;
import nutes.intelimed.model.DAO.IModelAnswersDao;
import nutes.intelimed.model.DAO.IModelEdgeDao;
import nutes.intelimed.model.DAO.IModelNodeDao;
import nutes.intelimed.model.entity.Answer;
import nutes.intelimed.model.entity.Edge;
import nutes.intelimed.model.entity.Node;
import android.content.Context;
import android.util.Log;

public class TreeUpdate implements ITreeUpdate {
	protected static final String CATEGORIA = "nutes";

	private static IModelEdgeDao edgeDao;
	private static IModelNodeDao nodeDao;
	private static IModelAnswersDao answersDao;
	private Edge entEdge;
	private Node node;
	private Context ctx;

	/**
	 * M�todo construtor
	 * 
	 * @param contexto o qual ser� utilizado o TreeUpdate
	 */
	public TreeUpdate(Context context) {
		this.ctx = context;
		edgeDao = (IModelEdgeDao) new EdgeScript(ctx);
		answersDao = (IModelAnswersDao) new AnswerScript(ctx);
		nodeDao = (IModelNodeDao) new NodeScript(ctx);
		
		nodeDao.deleteNode();
		answersDao.deleteAnswer();
		edgeDao.deleteEdge();
		
		entEdge = new Edge();
		node = new Node();
	}

	public void insertNode(Long noId, String noDescricao, boolean diagnostico) {
		node.setIdno(noId);
		node.setDescricaoNo(noDescricao);
		
		if(diagnostico == true)	node.setDiagnostico(1);
		else node.setDiagnostico(0);
		
		Log.i(CATEGORIA, "dentro do insertNode");
		nodeDao.insertNode(node);
		
	}

	public void insertNodeAnswers(Long respostaNoId, String respostaNoDescricao, Long codeResposta) {
		Answer answer = new Answer(node.getIdno(), respostaNoId, respostaNoDescricao, codeResposta);
		
		Log.i(CATEGORIA, "dentro do insertresposta");

		answersDao.insertAnswer(answer);
	}

	public void insertNodeEdge(Long arestaId, Long respostaId) {
		entEdge.setFk_idno(node.getIdno());
		entEdge.setIdaresta(arestaId);
		entEdge.setFk_idresposta(respostaId);
		
		Log.i(CATEGORIA, "dentro do insertAresta");
		edgeDao.insertEdge(entEdge);
	}
	
	public void fechar(){
		edgeDao.fechar();
		nodeDao.fechar();
		answersDao.fechar();
	}
	
}
