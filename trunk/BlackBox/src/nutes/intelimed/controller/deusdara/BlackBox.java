package nutes.intelimed.controller.deusdara;

import java.util.ArrayList;
import java.util.Iterator;

import nutes.intelimed.controller.activity.FormDiagnostic;
import nutes.intelimed.controller.util.AnswerOption;
import nutes.intelimed.model.EdgeScript;
import nutes.intelimed.model.IModelEdge;
import nutes.intelimed.model.IModelNode;
import nutes.intelimed.model.NodeScript;
import nutes.intelimed.model.entity.Edge;
import nutes.intelimed.model.entity.Node;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Classe controladora das activities, reponsável pela engine inteligente
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class BlackBox {

	public static IModelEdge edgeDao;
	public static IModelNode nodeDao;
	private Edge entEdge;
	private Node node;
	private Context ctx;

	public BlackBox(Context context) {
		this.ctx = context;
		edgeDao = (IModelEdge) new EdgeScript(ctx);
		nodeDao = (IModelNode) new NodeScript(ctx);
	}

	/**
	 * Método que processa resultado
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param arrQuest - array de respostas
	 * @param arrNO
	 * @param arrayJason - array de respostas em JSON
	 * @param treeObj - objeto JSON com respostas
	 * @param context
	 * @param edgeDao2
	 * @return String com questões e suas respectivas respostas
	 *         marcadas
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

	/**
	 * Monta uma questão específica
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param pergunta - pergunta que se deseja imprimir na tela
	 * @param idno - identificador da pergunta
	 * @param formDiagnostic - tela de questionário
	 * @return TextView com uma questão específica
	 */
	public TextView createQuestionLabel(String pergunta, long idno,
			FormDiagnostic formDiagnostic) {
		TextView perguntas = null;
		perguntas = new TextView(formDiagnostic);
		perguntas.setId((int) idno);
		perguntas.setText(pergunta);

		return perguntas;
	}

	/**
	 * Monta as opções de respostas de uma questão específica
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param questionOption - array de respostas
	 * @param radioId - identificador da pergunta
	 * @param idno - identificador da pergunta
	 * @param formDiagnostic - tela de questionário
	 * @return View com radio group de um questão específica
	 */
	public View createQuestionOption(ArrayList<AnswerOption> questionOption,
			int radioId, FormDiagnostic formDiagnostic) {

		RadioGroup radio_group = new RadioGroup(formDiagnostic);
		radio_group.setTag(radioId);
		RadioButton radio_button;
		Iterator<AnswerOption> option = questionOption.iterator();
		while (option.hasNext()) {
			AnswerOption nextOption = option.next();
			radio_button = new RadioButton(formDiagnostic);
			radio_button.setId(nextOption.codeResposta);
			radio_button.setTag(nextOption.fk_idno);
			radio_button.setText(nextOption.resposta);
			radio_group.addView(radio_button);
		}
		radio_group.setOnCheckedChangeListener(formDiagnostic);

		return radio_group;
	}

}
