package nutes.intelimed.controller.activity;

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
public class DiagnosticForm extends Activity{
	RadioGroup rQuest1,rQuest2,rQuest3,rQuest4,rQuest5,rQuest6,rQuest7,rQuest8,rQuest9,rQuest10,
    rQuest11,rQuest12,rQuest13,rQuest14,rQuest15,rQuest16,rQuest17,rQuest18,rQuest19,rQuest20,rQuest21,rQuest22,
    rQuest23,rQuest24,rQuest25,rQuest26,rQuest27,rQuest28;
	Button validar;
	String[] arrQuest = new String[4];
	String[] arrQuestIndex = new String[4];
	JSONArray arrayJason;
	ImageButton back, logout;
	BlackBox treeQ;
	JSONObject treeObj;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire_asma);
        
        rQuest1 = (RadioGroup) findViewById(R.id.rq1);
      
        rQuest2 = (RadioGroup) findViewById(R.id.rq2);
        rQuest3 = (RadioGroup) findViewById(R.id.rq3);
        rQuest4 = (RadioGroup) findViewById(R.id.rq4);
        
        validar = (Button) findViewById(R.id.ok);
       
        rQuest1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				RadioButton q1 = (RadioButton) findViewById(checkedId);
				
				arrQuest[0] = q1.getTag().toString();
				arrQuestIndex[0] = Integer.toString(checkedId);
				
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
                
        validar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				arrayJason = new JSONArray();
				treeObj = new JSONObject();
				treeQ = new BlackBox();
				for(int i=0; i<arrQuest.length;i++)
				{
					try {
						treeObj.put(arrQuestIndex[i], arrQuest[i]);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					arrayJason.put(treeObj);
					treeObj = new JSONObject();
				}
					System.out.println(arrayJason);
					
					Intent it = new Intent(getBaseContext(),DiagnosticResult.class);
					it.putExtra("questionnaireData", treeQ.controlTree(arrayJason));
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