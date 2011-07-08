package nutes.intelimed.controller.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nutes.intelimed.R;
import nutes.intelimed.controller.TreeQuestionnaire;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionnaireAsma extends Activity{
	RadioGroup rQuest1,rQuest2,rQuest3,rQuest4,rQuest5,rQuest6,rQuest7,rQuest8,rQuest9,rQuest10,
    rQuest11,rQuest12,rQuest13,rQuest14,rQuest15,rQuest16,rQuest17,rQuest18,rQuest19,rQuest20,rQuest21,rQuest22,
    rQuest23,rQuest24,rQuest25,rQuest26,rQuest27,rQuest28;
	Button validar;
	String[] arrQuest = new String[4];
	JSONArray arrayJason;
	ImageButton back, logout;
	TreeQuestionnaire treeQ;
	JSONObject treeObj;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire_asma);
        
        back = (ImageButton) findViewById(R.bt.btBack);
		logout = (ImageButton) findViewById(R.bt.btLogoff);
        
        rQuest1 = (RadioGroup) findViewById(R.id.rq1);
        rQuest2 = (RadioGroup) findViewById(R.id.rq2);
        rQuest3 = (RadioGroup) findViewById(R.id.rq3);
        rQuest4 = (RadioGroup) findViewById(R.id.rq4);
        /*rQuest5 = (RadioGroup) findViewById(R.id.rq5);
        rQuest6 = (RadioGroup) findViewById(R.id.rq6);
        rQuest7 = (RadioGroup) findViewById(R.id.rq7);
        rQuest8 = (RadioGroup) findViewById(R.id.rq8);
        rQuest9 = (RadioGroup) findViewById(R.id.rq9);
        rQuest10 = (RadioGroup) findViewById(R.id.rq10);
        rQuest11 = (RadioGroup) findViewById(R.id.rq11);
        rQuest12 = (RadioGroup) findViewById(R.id.rq12);
        rQuest13 = (RadioGroup) findViewById(R.id.rq13);
        rQuest14 = (RadioGroup) findViewById(R.id.rq14);
        rQuest15 = (RadioGroup) findViewById(R.id.rq15);
        rQuest16 = (RadioGroup) findViewById(R.id.rq16);
        rQuest17 = (RadioGroup) findViewById(R.id.rq17);
        rQuest18 = (RadioGroup) findViewById(R.id.rq18);
        rQuest19 = (RadioGroup) findViewById(R.id.rq19);
        rQuest20 = (RadioGroup) findViewById(R.id.rq20);
        rQuest21 = (RadioGroup) findViewById(R.id.rq21);
        rQuest22 = (RadioGroup) findViewById(R.id.rq22);
        rQuest23 = (RadioGroup) findViewById(R.id.rq23);
        rQuest24 = (RadioGroup) findViewById(R.id.rq24);
        rQuest25 = (RadioGroup) findViewById(R.id.rq25);
        rQuest26 = (RadioGroup) findViewById(R.id.rq26);
        rQuest27 = (RadioGroup) findViewById(R.id.rq27);
        rQuest28 = (RadioGroup) findViewById(R.id.rq28);*/
        
        
        validar = (Button) findViewById(R.id.ok);
       
        
        
        rQuest1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[0] =  Integer.toString(checkedId);
				RadioButton teste1 = (RadioButton) findViewById(checkedId);
				//arrQuest[0] =  teste.getText().toString();
				arrQuest[0] = teste1.getTag().toString();
				//arrQuest[0] =  Integer.toString(teste.getId());
				
				//arrayJason.put(arrQuest[0]);
			}
		});
        
        rQuest2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[1] =  Integer.toString(checkedId);
				RadioButton q1 = (RadioButton) findViewById(checkedId);
				arrQuest[1] = q1.getTag().toString();
				
			}
		});
        
        rQuest3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[2] =  Integer.toString(checkedId);
				RadioButton q2 = (RadioButton) findViewById(checkedId);
				arrQuest[2] = q2.getTag().toString();
				//arrayJason.put(arrQuest[2]);
			}
		});
        rQuest4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[3] =  Integer.toString(checkedId);
				RadioButton q3 = (RadioButton) findViewById(checkedId);
				arrQuest[3] = q3.getTag().toString();
			}
		});
        /*
        rQuest5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[4] =  Integer.toString(checkedId);
				RadioButton q4 = (RadioButton) findViewById(checkedId);
				arrQuest[4] = q4.getTag().toString();
			
			}
		});
        rQuest6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[5] =  Integer.toString(checkedId);
				RadioButton q5 = (RadioButton) findViewById(checkedId);
				arrQuest[5] = q5.getTag().toString();
			}
		});
        rQuest7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[6] =  Integer.toString(checkedId);
				RadioButton q6 = (RadioButton) findViewById(checkedId);
				arrQuest[6] = q6.getTag().toString();
				
			}
		});
        rQuest8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[7] =  Integer.toString(checkedId);
				RadioButton q7 = (RadioButton) findViewById(checkedId);
				arrQuest[7] = q7.getTag().toString();
				
			}
		});
        rQuest9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[8] =  Integer.toString(checkedId);
				RadioButton q8 = (RadioButton) findViewById(checkedId);
				arrQuest[8] = q8.getTag().toString();
				
			}
		});
        rQuest10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[9] =  Integer.toString(checkedId);
				RadioButton q9 = (RadioButton) findViewById(checkedId);
				arrQuest[9] = q9.getTag().toString();
				
			}
		});
        rQuest11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[10] =  Integer.toString(checkedId);
				RadioButton q10 = (RadioButton) findViewById(checkedId);
				arrQuest[10] = q10.getTag().toString();
				
			}
		});
        rQuest12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[11] =  Integer.toString(checkedId);
				RadioButton q11 = (RadioButton) findViewById(checkedId);
				arrQuest[11] = q11.getTag().toString();
				
			}
		});
        rQuest13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[12] =  Integer.toString(checkedId);
				RadioButton q12 = (RadioButton) findViewById(checkedId);
				arrQuest[12] = q12.getTag().toString();
				
			}
		});
        rQuest14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[13] =  Integer.toString(checkedId);
				RadioButton q13 = (RadioButton) findViewById(checkedId);
				arrQuest[13] = q13.getTag().toString();
				
			}
		});
        rQuest15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[14] =  Integer.toString(checkedId);
				RadioButton q14 = (RadioButton) findViewById(checkedId);
				arrQuest[14] = q14.getTag().toString();
				
			}
		});
        rQuest16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[15] =  Integer.toString(checkedId);
				RadioButton q15 = (RadioButton) findViewById(checkedId);
				arrQuest[15] = q15.getTag().toString();
				
			}
		});
        rQuest17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[16] =  Integer.toString(checkedId);
				RadioButton q16 = (RadioButton) findViewById(checkedId);
				arrQuest[16] = q16.getTag().toString();
			
			}
		});
        rQuest18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[17] =  Integer.toString(checkedId);
				RadioButton q17 = (RadioButton) findViewById(checkedId);
				arrQuest[17] = q17.getTag().toString();
			
			}
		});
        rQuest19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[18] =  Integer.toString(checkedId);
				RadioButton q18 = (RadioButton) findViewById(checkedId);
				arrQuest[18] = q18.getTag().toString();
			
			}
		});
        rQuest20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[19] =  Integer.toString(checkedId);
				RadioButton q19 = (RadioButton) findViewById(checkedId);
				arrQuest[19] = q19.getTag().toString();
			
			}
		});
        rQuest21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[20] =  Integer.toString(checkedId);
				RadioButton q20 = (RadioButton) findViewById(checkedId);
				arrQuest[20] = q20.getTag().toString();
			
			}
		});
        rQuest22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[21] =  Integer.toString(checkedId);
				RadioButton q21 = (RadioButton) findViewById(checkedId);
				arrQuest[21] = q21.getTag().toString();
			
			}
		});
        rQuest23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[22] =  Integer.toString(checkedId);
				RadioButton q22 = (RadioButton) findViewById(checkedId);
				arrQuest[22] = q22.getTag().toString();
			
			}
		});
        rQuest24.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[23] =  Integer.toString(checkedId);
				RadioButton q23 = (RadioButton) findViewById(checkedId);
				arrQuest[23] = q23.getTag().toString();
			
			}
		});
        rQuest25.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[24] =  Integer.toString(checkedId);
				RadioButton q24 = (RadioButton) findViewById(checkedId);
				arrQuest[24] = q24.getTag().toString();
			
			}
		});
        rQuest26.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[25] =  Integer.toString(checkedId);
				RadioButton q25 = (RadioButton) findViewById(checkedId);
				arrQuest[25] = q25.getTag().toString();
			
			}
		});
        rQuest27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[26] =  Integer.toString(checkedId);
				RadioButton q26 = (RadioButton) findViewById(checkedId);
				arrQuest[26] = q26.getTag().toString();
			}
		});
        rQuest28.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				//arrQuest[27] =  Integer.toString(checkedId);
				RadioButton q27 = (RadioButton) findViewById(checkedId);
				arrQuest[27] = q27.getTag().toString();
			}
		});*/
        
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//finish();
				startActivity(new Intent(getBaseContext(),MainMenu.class));
				
			}
		});
		
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), Login.class));
				
			}
		});
		
        validar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrayJason = new JSONArray();
				treeObj = new JSONObject();
				int cont = 1;
				treeQ = new TreeQuestionnaire();
				for(int i=0; i<arrQuest.length;i++)
				{
					try {
						treeObj.put("Q"+cont, arrQuest[i]);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					arrayJason.put(arrQuest[i]);
					cont++;
				}
				
								
				
					/*System.out.println(arrayJason);
					System.out.println(treeObj);*/
				
				
				Intent it = new Intent(getBaseContext(),ResultQuestionnaire.class);
				it.putExtra("questionnaireData", treeQ.controlTree(arrQuest,arrayJason, treeObj));
				startActivity(it);
				
				
				
				
				
				
				
			}
			
		});
    }
    
    /**
	 * @author jamilson
	 * @Description Implementation for button  back of Activity
	 * @param Indentification of onclick for mouse
	 * @return value boolean
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	startActivity(new Intent(getBaseContext(), MainMenu.class));
	    	//finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
    @Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada na tela pendente
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}
}