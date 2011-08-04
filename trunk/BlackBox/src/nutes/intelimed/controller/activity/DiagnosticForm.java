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
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe respons�vel pela montagem do formul�rio de diagn�stico na tela
 */
public class DiagnosticForm extends Activity implements OnCheckedChangeListener {

	public static IModelStructureQuestionnaire dao;
	
	private Button validar;
	private String[] arrQuest = new String[50];
	private JSONArray arrayJason;
	private BlackBox treeQ;
	private JSONObject treeObj;
	private LinearLayout linerLayout;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_asma);

		dao = (IModelStructureQuestionnaire) new StructureQuestionnaireScript(
				this);
		montarQuest();

		validar = new Button(this);
		validar.setText("OK");
        linerLayout.addView(validar);
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
	 * @Description O m�todo � chamado ao clicar em uma resposta, armazenando o valor em array
	 * @param group -  inst�ncia do RadioGroup
	 * @param checkedId - �ndice da resposta selecionada
	 * @return void
	 */
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		arrQuest[Integer.parseInt(group.getTag().toString())] = Integer
				.toString(checkedId);
	}
	
	/**
	 * @Description M�todo respos�vel por montar as quest�es do question�rio dinamicamente
	 * @return void
	 */
	public void montarQuest() {

		ArrayList<StructureQuestionnaire> arrayQuestion = (ArrayList<StructureQuestionnaire>) dao
				.listarEstruturaQuestionario();
		treeQ = new BlackBox();
		linerLayout = (LinearLayout) findViewById(R.id.LinearLayout02);
		ArrayList<String> questionOption = new ArrayList<String>();

		int aux;
		int aux2 = 0;
		StructureQuestionnaire vAux;
		
		for (int i = 0; i < arrayQuestion.size(); i++) {
			aux = i;
			vAux = arrayQuestion.get(aux);
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
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}
}
