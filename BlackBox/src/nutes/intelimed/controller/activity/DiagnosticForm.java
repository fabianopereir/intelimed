package nutes.intelimed.controller.activity;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.controller.deusdara.BlackBox;
import nutes.intelimed.model.InterfaceModelStructureQuestionnaire;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe respons�vel pela montagem do formul�rio de diagn�stico
 */
public class DiagnosticForm extends Activity {
	
	public static InterfaceModelStructureQuestionnaire dao;
	RadioGroup rQuest1, rQuest2, rQuest3, rQuest4;
	private List<StructureQuestionnaire> perguntas;
	
	ArrayList<RadioGroup> arrQuestions = new ArrayList<RadioGroup>();
	Button validar;
	String[] arrQuest = new String[4];
	JSONArray arrayJason;
	ImageButton back, logout;
	BlackBox treeQ;
	JSONObject treeObj;
	int i;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_asma);
		
		dao = new StructureQuestionnaireScript(this);
		montarQuest();
		rQuest1 = (RadioGroup) findViewById(R.id.rq1);
		rQuest2 = (RadioGroup) findViewById(R.id.rq2);
		rQuest3 = (RadioGroup) findViewById(R.id.rq3);
		rQuest4 = (RadioGroup) findViewById(R.id.rq4);

		arrQuestions.add(rQuest1);
		arrQuestions.add(rQuest2);
		arrQuestions.add(rQuest3);
		arrQuestions.add(rQuest4);

		validar = (Button) findViewById(R.id.ok);
		
		respostaQuestao();
		
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
				System.out.println(arrayJason);
				System.out.println(treeObj);

				Intent it = new Intent(getBaseContext(), DiagnosticResult.class);
				it.putExtra("questionnaireData",
						treeQ.controlTree(arrQuest, arrayJason, treeObj));
				startActivity(it);
			}

		});
	}
	public void montarQuest()
	{
		dao.listarPerguntas();
		String teste;
		teste = "1";
	}
	
	// itera em todas as quest�es e captura suas respectivas respostas
	// armazenando-as em um array
	public void respostaQuestao() {
		
		for (i = 0; i < arrQuestions.size(); i++) {	
			//quando o RadioButton da quest�o i � marcado, ent�o � chamado o seguinte m�todo
			arrQuestions.get(i).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				    for(int cont=0;cont<arrQuest.length;cont++){  

				    	RadioButton answer = (RadioButton) findViewById(checkedId);
				    	
				    	//captura resposta da quest�o i e armazena em array na posi��o i
				    	arrQuest[cont] = answer.getTag().toString();
						System.out.println("arrQuest["+cont+"]"+arrQuest[cont]);    
				      }  
				}
				
			});	
			System.out.println("i"+i);

		}
	}
	


	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}
}