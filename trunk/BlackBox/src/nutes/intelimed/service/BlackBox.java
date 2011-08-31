package nutes.intelimed.service;

import nutes.intelimed.model.EdgeScript;
import nutes.intelimed.model.NodeScript;
import nutes.intelimed.model.DAO.IModelEdgeDao;
import nutes.intelimed.model.DAO.IModelNodeDao;
import nutes.intelimed.model.entity.Edge;
import nutes.intelimed.model.entity.Node;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

/**
 * Classe responsável pelo engenho inteligente
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class BlackBox implements IBlackBox {

	public static IModelEdgeDao edgeDao;
	public static IModelNodeDao nodeDao;
	private Edge entEdge;
	private Node node;
	private Context ctx;

	/**
	 * Método construtor
	 * @param Context - contexto
	 */
	public BlackBox(Context context) {
		this.ctx = context;
		edgeDao = (IModelEdgeDao) new EdgeScript(ctx);
		nodeDao = (IModelNodeDao) new NodeScript(ctx);
	}

	/**
	 * Método responsável por percorrer a árvore de decisão e retornar o diagnóstico
	 * @param arrQuest - array de respostas
	 * @param arrayJason - array de respostas em JSON
	 * @param treeObj - objeto JSON com respostas
	 * @param arrNO - array de nós
	 * @return resultado do diagnóstico, tipo String
	 */
	public String controlTree(String[] arrQuest, JSONArray arrayJason,JSONObject treeObj, String[] arrNO) {
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
}
