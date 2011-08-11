package nutes.intelimed.controller.activity;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.controller.deusdara.BlackBox;
import nutes.intelimed.controller.util.AnswerOption;
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
import android.widget.Toast;

/**
 * Classe respons�vel pela montagem do formul�rio de diagn�stico na
 *              tela
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class DiagnosticForm extends Activity implements OnCheckedChangeListener {

	public static IModelStructureQuestionnaire dao;

	private Button validar;
	private String[] arrQuest;
	private String[] arrNO;
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
				validar();
			}
		});
	};
	
	/**
	 * M�todo respons�vel pela valida��o das respostas e passagem para DiagnosticResult, 
	 * chamado quando o bot�o "OK" � clicado
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @return void
	 */
	public void validar() {
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

		List<String> stringListWithouNull = new ArrayList<String>();
		List<String> stringListWithouNullNO = new ArrayList<String>();

		for(int i=0;i<arrQuest.length; i++) { 
		    if(arrQuest[i] != null){
		    	stringListWithouNull.add(arrQuest[i]);
		    }
		}
		
		for(int i=0;i<arrNO.length; i++) { 
		    if(arrNO[i] != null){
		    	stringListWithouNullNO.add(arrQuest[i]);
		    }
		}
		
		if(stringListWithouNull.size() == stringListWithouNullNO.size()){
			Intent it = new Intent(getBaseContext(), DiagnosticResult.class);
			it.putExtra("answer",arrQuest);
			it.putExtra("no",arrNO);
			it.putExtra("diagnostic",treeQ.controlTree(arrQuest, arrayJason, treeObj,arrNO));
			startActivity(it);
		}else{
			Toast.makeText(DiagnosticForm.this, "Alguma pergunta n�o foi respondida. Favor responder todas as perguntas.", Toast.LENGTH_SHORT).show();

		}
		
	}

	/**
	 * O m�todo � chamado ao clicar em uma resposta, armazenando o valor em array
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param group - inst�ncia do RadioGroup
	 * @param checkedId - �ndice da resposta selecionada
	 * @return void
	 */
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		arrQuest[Integer.parseInt(group.getTag().toString())] = Integer.toString(checkedId);

	}

	/**
	 * M�todo respons�vel por montar as quest�es do question�rio dinamicamente
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
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
		arrNO = new String[arrayQuestion.size()];
		arrQuest = new String[arrayQuestion.size()];
		for (int i = 0; i < arrayQuestion.size(); i++) {
			aux = i;
			vAux = arrayQuestion.get(aux);
			linerLayout.addView(treeQ.createQuestionLabel(arrayQuestion.get(i).getDescricao_no(), arrayQuestion.get(i).getIdno(), this));

			while (arrayQuestion.get(i).getIdno() == vAux.getIdno() && aux < arrayQuestion.size()) {
				option = new AnswerOption();
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
			linerLayout.addView(treeQ.createQuestionOption(questionOption,aux2, this));
			arrNO[aux2] = Integer.toString(arrayQuestion.get(aux - 1).getFk_idno());
			questionOption.clear();
			i = aux - 1;
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// finish();
	}
}
