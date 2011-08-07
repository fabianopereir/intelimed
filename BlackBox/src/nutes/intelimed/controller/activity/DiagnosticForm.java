package nutes.intelimed.controller.activity;

import java.util.ArrayList;

import nutes.intelimed.controller.deusdara.BlackBox;
import nutes.intelimed.model.IModelStructureQuestionnaire;
import nutes.intelimed.model.StructureQuestionnaireScript;
import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.util.AnswerOption;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe responsável pela montagem do formulário de diagnóstico na tela
 */
public class DiagnosticForm extends Activity implements OnCheckedChangeListener {

	public static IModelStructureQuestionnaire dao;
	
	private Button validar;
	private String[] arrQuest = new String[50];
	private String[] arrNO = new String[50];
	private JSONArray arrayJason;
	private BlackBox treeQ;
	private JSONObject treeObj;
	private LinearLayout linerLayout;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_asma);

		dao = (IModelStructureQuestionnaire) new StructureQuestionnaireScript(this);
		
		montarQuest();

		validar = new Button(this);
		validar.setText("OK");
        linerLayout.addView(validar);
		validar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				arrayJason = new JSONArray();
				treeObj = new JSONObject();
				int cont = 1;
				treeQ = new BlackBox(getBaseContext());
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
				//Modificando
				it.putExtra("questionnaireData",treeQ.controlTree(arrQuest, arrayJason, treeObj,arrNO));
				//it.putExtra("questionnaireData",treeQ.controlTree(arrQuest,arrNO, arrayJason, treeObj));
				
				startActivity(it);
			}

		});
	}

	/**
	 * @Description O método é chamado ao clicar em uma resposta, armazenando o valor em array
	 * @param group -  instância do RadioGroup
	 * @param checkedId - índice da resposta selecionada
	 * @return void
	 */
	public void onCheckedChanged(RadioGroup group, int checkedId) {
	arrQuest[Integer.parseInt(group.getTag().toString())] = Integer.toString(checkedId);
	Log.i("jamilson", "Consegui "+ arrNO[Integer.valueOf(group.getTag().toString())]);
	
	//arrNO[Integer.parseInt(group.getTag().toString())] = Integer.toString(group.getId());
	}
	
	/**
	 * @Description Método responsável por montar as questões do questionário dinamicamente
	 * @return void
	 */
	public void montarQuest() {

		ArrayList<StructureQuestionnaire> arrayQuestion = (ArrayList<StructureQuestionnaire>) dao.listarEstruturaQuestionario();
		treeQ = new BlackBox(getBaseContext());
		linerLayout = (LinearLayout) findViewById(R.id.LinearLayout02);
		ArrayList<AnswerOption> questionOption = new ArrayList<AnswerOption>();

		int aux;
		int aux2 = 0;
		StructureQuestionnaire vAux;
		AnswerOption option;
		for (int i = 0; i < arrayQuestion.size(); i++) {
			aux = i;
			vAux = arrayQuestion.get(aux);
				linerLayout.addView(treeQ.createTypeMetrics(arrayQuestion.get(i).getDescricao_no(),arrayQuestion.get(i).getIdno(), this));
				
				while (arrayQuestion.get(i).getIdno() == vAux.getIdno() && aux < arrayQuestion.size()) {
					option = new AnswerOption();
					//option.codeResposta = arrayQuestion.get(aux).getCodeResposta();
					option.codeResposta = arrayQuestion.get(aux).getIdresposta();
					option.fk_idno = arrayQuestion.get(aux).getFk_idno();
					option.resposta = arrayQuestion.get(aux).getDescricao_resposta();
					
					questionOption.add(option);
					
					aux++;
					
					if (aux < arrayQuestion.size()) {
						vAux = arrayQuestion.get(aux);
					}
				}
				aux2++;
				//Modificando para verificar no posterior
				linerLayout.addView(treeQ.createTypeMetricsG(questionOption,aux2, this));
				arrNO[aux2] = Integer.toString(arrayQuestion.get(aux-1).getFk_idno());
				//linerLayout.addView(treeQ.createTypeMetricsG(questionOption,aux2,arrayQuestion.get(i).getIdno(), this));
				questionOption.clear();
				i = aux - 1;
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		//finish();
	}
}
