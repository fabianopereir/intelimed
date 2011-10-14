package nutes.intelimed.controller.diagnostic;

import java.util.List;

import nutes.intelimed.model.diagnostic.IModelStructureQuestionnaireDao;
import nutes.intelimed.model.diagnostic.StructureQuestionnaire;
import nutes.intelimed.model.diagnostic.StructureQuestionnaireDao;
import nutes.intelimed.model.tree.Edge;
import nutes.intelimed.model.tree.EdgeDao;
import nutes.intelimed.model.tree.IModelEdgeDao;
import nutes.intelimed.model.tree.IModelNodeDao;
import nutes.intelimed.model.tree.Node;
import nutes.intelimed.model.tree.NodeDao;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;


/**
 * Classe respons�vel pelo engenho inteligente
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class DiagnosticController implements IDiagnostic {
	protected static final String CATEGORIA = "nutes";

	private IModelEdgeDao edgeDao;
	private IModelNodeDao nodeDao;
	private IModelStructureQuestionnaireDao questionnaireDao;
	private Edge entEdge;
	private Node node;
	private Context ctx;

	/**
	 * M�todo construtor
	 * 
	 * @param Context
	 *            - contexto o qual ser� utilizado o BlackBox
	 */
	public DiagnosticController(Context context) {
		this.ctx = context;
		edgeDao = (IModelEdgeDao) new EdgeDao(ctx);
		nodeDao = (IModelNodeDao) new NodeDao(ctx);
		questionnaireDao = (IModelStructureQuestionnaireDao) new StructureQuestionnaireDao(ctx);
	}

	/**
	 * M�todo respons�vel por percorrer a �rvore de decis�o e retornar o
	 * diagn�stico
	 * 
	 * @param arrQuest
	 *            - array de respostas
	 * @param arrayJason
	 *            - array de respostas em JSON
	 * @param treeObj
	 *            - objeto JSON com respostas
	 * @param arrNO
	 *            - array de n�s
	 * @return resultado do diagn�stico, tipo String
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

	public List<StructureQuestionnaire> listarEstruturaQuestionario() {
		return this.questionnaireDao.listarEstruturaQuestionario();
	}
}
