package nutes.intelimed.service;

import nutes.intelimed.model.AnswerScript;
import nutes.intelimed.model.EdgeScript;
import nutes.intelimed.model.NodeScript;
import nutes.intelimed.model.DAO.IModelAnswersDao;
import nutes.intelimed.model.DAO.IModelEdgeDao;
import nutes.intelimed.model.DAO.IModelNodeDao;
import nutes.intelimed.model.entity.Answer;
import nutes.intelimed.model.entity.Edge;
import nutes.intelimed.model.entity.Node;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * Classe responsável pelo engenho inteligente
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class BlackBox implements IBlackBox {
	protected static final String CATEGORIA = "nutes";


	public static IModelEdgeDao edgeDao;
	public static IModelNodeDao nodeDao;
	public static IModelAnswersDao answersDao;
	private Edge entEdge;
	private Node node;
	private Context ctx;
	private Answer answer;

	/**
	 * Método construtor
	 * 
	 * @param Context
	 *            - contexto o qual será utilizado o BlackBox
	 */
	public BlackBox(Context context) {
		this.ctx = context;
		edgeDao = (IModelEdgeDao) new EdgeScript(ctx);
		answersDao = (IModelAnswersDao) new AnswerScript(ctx);
		nodeDao = (IModelNodeDao) new NodeScript(ctx);
	}

	/**
	 * Método responsável por percorrer a árvore de decisão e retornar o
	 * diagnóstico
	 * 
	 * @param arrQuest
	 *            - array de respostas
	 * @param arrayJason
	 *            - array de respostas em JSON
	 * @param treeObj
	 *            - objeto JSON com respostas
	 * @param arrNO
	 *            - array de nós
	 * @return resultado do diagnóstico, tipo String
	 */
	public String controlTree(String[] arrQuest, JSONArray arrayJason,
			JSONObject treeObj, String[] arrNO) {
		String res = null;
		entEdge = new Edge();
		node = new Node();

		for (int i = 0; i < arrQuest.length; i++) {
			if (arrQuest[i] != null) {
				entEdge = edgeDao.searchEdge(Long.parseLong(arrQuest[i]));

				node = nodeDao.searchNode(entEdge.getFk_idno());
				if (node != null) {
					res = node.getDescricaoNo();
					break;
				}
				for (int j = 0; j < arrNO.length; j++) {
					if (arrNO[j] != null) {
						if (arrNO[j].equals(entEdge.getFk_idno().toString())) {
							i = j - 1;
							break;
						}

					}
				}
			}
		}

		return res;
	}

	/*public void insertEdge(Long arestaId, Long respostaId, String respostaDescricao) {
		entEdge.setIdaresta(arestaId);
		entEdge.setFk_idresposta(respostaId);
		
		
		edgeDao.insertEdge(entEdge);

	}*/

	
	public void insertNode(Long noId, String noDescricao) {
		
		node = new Node();
		node.setIdno(noId);
		node.setDescricaoNo(noDescricao);
		//TODO se não houverem respostas para o nó -> node.setDiagnostico(1);

        Log.i(CATEGORIA,"dentro do insertNode");
		nodeDao.insertNode(node);
	}

	
	public void insertNodeAnswers(Long respostaNoId, String respostaNoDescricao) {
		answer = new Answer();
		answer.setIdresposta(respostaNoId);
		answer.setDescricao_resposta(respostaNoDescricao);
	
		answer.setFk_idno(node.getIdno());
		
        Log.i(CATEGORIA,"dentro do insertresposta");

		answersDao.insertAnswer(answer);
	}

	
	public void insertNodeEdge(Long arestaId, Long respostaId) {
		entEdge = new Edge();
		entEdge.setFk_idno(node.getIdno());
		entEdge.setIdaresta(arestaId);
		entEdge.setFk_idresposta(respostaId);
        Log.i(CATEGORIA,"dentro do insertAresta");

		edgeDao.insertEdge(entEdge);
	}
	
	
	/*
	public void insertAnswers(Long respostaId, String respostaDescricao) {
		answer.setIdresposta(respostaId);
		answer.setDescricao_resposta(respostaDescricao);
		
		answersDao.insertAnswer(answer);

	}
	*/
	
}
