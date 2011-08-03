package nutes.intelimed.controller.activity;

import java.util.ArrayList;

import nutes.intelimed.controller.deusdara.BlackBox;
import nutes.intelimed.model.IModelStructureQuestionnaire;
import nutes.intelimed.model.StructureQuestionnaireScript;
import nutes.intelimed.model.entity.StructureQuestionnaire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela montagem do formulário de diagnóstico
 */
public class DiagnosticForm extends Activity implements OnCheckedChangeListener {

	public static IModelStructureQuestionnaire dao;
	RadioGroup rQuest1, rQuest2, rQuest3;

	ArrayList<RadioGroup> arrQuestions = new ArrayList<RadioGroup>();
	Button validar;
	String[] arrQuest = new String[50];
	JSONArray arrayJason;
	ImageButton back, logout;
	BlackBox treeQ;
	JSONObject treeObj;
	LinearLayout linerLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_asma);

		dao = (IModelStructureQuestionnaire) new StructureQuestionnaireScript(
				this);
		montarQuest();

		validar = (Button) findViewById(R.id.ok);

		validar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				arrayJason = new JSONArray();
				treeObj = new JSONObject();
				int cont = 1;
				treeQ = new BlackBox();
				for (int i = 0; i < arrQuest.length; i++) {
					try {
						treeObj.put("Q" + cont, arrQuest[i]);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					arrayJason.put(arrQuest[i]);
					cont++;
				}

				Intent it = new Intent(getBaseContext(), DiagnosticResult.class);
				it.putExtra("questionnaireData",
						treeQ.controlTree(arrQuest, arrayJason, treeObj));
				startActivity(it);
			}

		});
	}

	/**
	 * Evento é disparado ao clicar em um radio button (que são resposta), 
	 * armazenando o valor em um array de respostas assinaladas chamado arrQuest
	 * 
	 * @param group, checkedId
	 */
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		arrQuest[Integer.parseInt(group.getTag().toString())] = Integer
				.toString(checkedId);
		
	}
	/**
	 * Método resposável por montar as questões do questionário dinamicamente
	 */
	public void montarQuest() {
		//Array de cada pergunta com suas respectivas opções de resposta 
		ArrayList<StructureQuestionnaire> arrayQuestion = (ArrayList<StructureQuestionnaire>) dao
				.listarEstruturaQuestionario();

		treeQ = new BlackBox();
		linerLayout = (LinearLayout) findViewById(R.id.LinearLayout02);

		ArrayList<String> questionOption = new ArrayList<String>();

		int aux;
		int aux2 = 0;
		StructureQuestionnaire vAux;
		
		//itera em cada questão completa (questão e alternativas)
		for (int i = 0; i < arrayQuestion.size(); i++) {
			aux = i;
			
			//armazena em um array de questões completas
			vAux = arrayQuestion.get(aux);
			//if (arrayQuestion.get(i).getTipo().equals("radio group")) {
				linerLayout.addView(treeQ.createTypeMetrics(arrayQuestion.get(i).getDescricao_no(), this));
				
				while (arrayQuestion.get(i).getIdno() == vAux.getIdno() && aux < arrayQuestion.size()) {
					
					questionOption.add(arrayQuestion.get(aux).getDescricao_resposta());
					aux++;
					
					if (aux < arrayQuestion.size()) {
						vAux = arrayQuestion.get(aux);
					}
				}
				aux2++;
				linerLayout.addView(treeQ.createTypeMetricsG(questionOption,
						aux2, this));
				questionOption.clear();
				i = aux - 1;
			//}
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}
}
