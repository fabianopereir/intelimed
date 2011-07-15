package nutes.intelimed.controller.activity;

import java.util.ArrayList;
import java.util.Vector;

import nutes.intelimed.controller.deusdara.BlackBox;

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
import android.widget.Toast;

/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela montagem do formulário de diagnóstico
 */
public class DiagnosticForm extends Activity {
	RadioGroup rQuest1, rQuest2, rQuest3, rQuest4;
	ArrayList<RadioGroup> arrQuestions = new ArrayList<RadioGroup>();
	Button validar;
	String[] arrQuest = new String[4];
	JSONArray arrayJason;
	ImageButton back, logout;
	BlackBox treeQ;
	JSONObject treeObj;
	int i;

	// itera em todas as questões e captura suas respectivas respostas
	// armazenando-as em um array
	public void respostaQuestao() {
		
		for (i = 0; i < arrQuestions.size(); i++) {	
			//quando o RadioButton da questão i é marcado, então é chamado o seguinte método
			arrQuestions.get(i).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				    for(int cont=0;cont<arrQuest.length;cont++){  

				    	RadioButton answer = (RadioButton) findViewById(checkedId);
				    	
				    	//captura resposta da questão i e armazena em array na posição i
				    	arrQuest[cont] = answer.getTag().toString();
						System.out.println("arrQuest["+cont+"]"+arrQuest[cont]);    
				      }  
				}
				
			});	
			System.out.println("i"+i);

		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_asma);
		
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
		

/*
		rQuest1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				RadioButton q1 = (RadioButton) findViewById(checkedId);

				arrQuest[0] = q1.getTag().toString();

			}
		});

		rQuest2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

				RadioButton q2 = (RadioButton) findViewById(checkedId);
				arrQuest[1] = q2.getTag().toString();

			}
		});

		rQuest3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				RadioButton q3 = (RadioButton) findViewById(checkedId);
				arrQuest[2] = q3.getTag().toString();

			}
		});
		rQuest4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

				RadioButton q4 = (RadioButton) findViewById(checkedId);
				arrQuest[3] = q4.getTag().toString();
			}
		});
*/
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

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}
}