package nutes.intelimed.controller.activity;

import org.json.JSONArray;

import nutes.intelimed.R;
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

public class QuestionnaireAsma extends Activity{
	RadioGroup rQuest1,rQuest2,rQuest3,rQuest4,rQuest5,rQuest6,rQuest7,rQuest8,rQuest9,rQuest10,
    rQuest11,rQuest12,rQuest13,rQuest14,rQuest15,rQuest16,rQuest17,rQuest18,rQuest19,rQuest20,rQuest21,rQuest22,
    rQuest23,rQuest24,rQuest25,rQuest26,rQuest27,rQuest28;
	Button validar;
	String[] arrQuest = new String[28];
	JSONArray arrayJason;
	ImageButton back, logout;
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
        rQuest5 = (RadioGroup) findViewById(R.id.rq5);
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
        rQuest28 = (RadioGroup) findViewById(R.id.rq28);
        
        
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
				RadioButton teste2 = (RadioButton) findViewById(checkedId);
				//arrQuest[0] =  teste.getText().toString();
				arrQuest[1] = teste2.getTag().toString();
				//arrQuest[1] =  Integer.toString(teste.getId());
				//arrayJason.put(arrQuest[1]);
			}
		});
        
        rQuest3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[2] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[2]);
			}
		});
        rQuest4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[3] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[3]);
			}
		});
        rQuest5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[4] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[4]);
			}
		});
        rQuest6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[5] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[5]);
			}
		});
        rQuest7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[6] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[6]);
			}
		});
        rQuest8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[7] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[7]);
			}
		});
        rQuest9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[8] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[8]);
			}
		});
        rQuest10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[9] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[9]);
			}
		});
        rQuest11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[10] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[10]);
			}
		});
        rQuest12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[11] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[11]);
			}
		});
        rQuest13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[12] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[12]);
			}
		});
        rQuest14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[13] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[13]);
			}
		});
        rQuest15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[14] =  Integer.toString(checkedId);
				//	arrayJason.put(arrQuest[14]);
			}
		});
        rQuest16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[15] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[16] =  Integer.toString(checkedId);
				//arrayJason.put(arrQuest[16]);
			}
		});
        rQuest18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[17] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[18] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[19] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[20] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[21] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[22] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest24.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[23] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest25.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[24] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest26.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[25] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[26] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        rQuest28.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				arrQuest[27] =  Integer.toString(checkedId);
				//arrayJason.put(checkedId);
			}
		});
        
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
				for(int i=0; i<arrQuest.length;i++)
				{
					arrayJason.put(arrQuest[i]);
				}
				System.out.println(arrayJason);
				Toast.makeText(QuestionnaireAsma.this, arrQuest[0]+"--"+arrQuest[1], Toast.LENGTH_LONG).show();
				
			}
		});
    }
}