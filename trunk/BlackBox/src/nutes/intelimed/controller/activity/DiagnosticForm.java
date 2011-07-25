package nutes.intelimed.controller.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nutes.intelimed.controller.deusdara.BlackBox;
import nutes.intelimed.model.InterfaceModelStructureQuestionnaire;
import nutes.intelimed.model.InterfaceModelStructureQuestionnaireTest;
import nutes.intelimed.model.StructureQuestionnaireScript;
import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.model.entity.StructureQuestionnaireTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela montagem do formulário de diagnóstico
 */
public class DiagnosticForm extends Activity implements OnCheckedChangeListener {
	
	public static InterfaceModelStructureQuestionnaireTest dao;
	RadioGroup rQuest1, rQuest2, rQuest3, rQuest4;
	private List<StructureQuestionnaireTest> estrutura;
	
	ArrayList<RadioGroup> arrQuestions = new ArrayList<RadioGroup>();
	Button validar;
	String[] arrQuest = new String[4];
	JSONArray arrayJason;
	ImageButton back, logout;
	BlackBox treeQ;
	JSONObject treeObj;
	int i;
	LinearLayout linerLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_asma_teste);
		
		dao = (InterfaceModelStructureQuestionnaireTest) new StructureQuestionnaireScript(this);
		montarQuest();
		
        /*RadioGroup radio_group = new RadioGroup ( this );
        RadioButton radio_button_1 = new RadioButton ( this );
        radio_button_1.setId ( 1 );
        RadioButton radio_button_2 = new RadioButton ( this );
        radio_button_2.setId ( 2 );
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams ( 100, 30 );
        radio_group.addView ( radio_button_1, 0, params );
        radio_group.addView ( radio_button_2, 1, params );
        radio_group.check ( 2 );
        radio_group.setOnCheckedChangeListener ( this );
        setContentView ( radio_group );*/
	        
		/*rQuest1 = (RadioGroup) findViewById(R.id.rq1);
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
		*/
	}
	 public void onCheckedChanged ( RadioGroup group, int checkedId ) 
	    {
			Toast.makeText(DiagnosticForm.this, Integer.toString(checkedId), Toast.LENGTH_SHORT).show();
	    }
	public void montarQuest()
	{
		ArrayList<StructureQuestionnaireTest>  arrayQuestion = (ArrayList<StructureQuestionnaireTest>) dao.listarEstruturaQuestionario();
		TextView perguntas = null;
		linerLayout = (LinearLayout) findViewById(R.id.LinearLayout02);
		Iterator itr = arrayQuestion.iterator(); 
		while(itr.hasNext()) {

		    //Object element = itr.next();
			StructureQuestionnaire element = (StructureQuestionnaire) itr.next();
		    if (element.getPergunta()!="" && element.getPergunta()!=null)
		    {
		    	/*RadioGroup radio_group = new RadioGroup ( this );
		        RadioButton radio_button_1 = new RadioButton ( this );
		        radio_button_1.setId ( 1 );
		        RadioButton radio_button_2 = new RadioButton ( this );
		        radio_button_2.setId ( 2 );
		        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams ( 100, 30 );
		        radio_group.addView ( radio_button_1, 0, params );
		        radio_group.addView ( radio_button_2, 1, params );
		        radio_group.check ( 2 );
		        radio_group.setOnCheckedChangeListener ( this );
		        setContentView ( radio_group );*/
		    	perguntas = new TextView(this);
		    	perguntas.setText(element.getPergunta());
		    	linerLayout.addView(perguntas);
		        
		    }
			System.out.print("jamilson "+element.getPergunta());
		    //System.out.print("jamilson "+element + " ");

		} 
		//setContentView (perguntas);
	}
	
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
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}
}