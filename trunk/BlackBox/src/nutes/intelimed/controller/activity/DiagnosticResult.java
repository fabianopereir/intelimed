package nutes.intelimed.controller.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela montagem da tela de questionário
 */
public class DiagnosticResult extends Activity {

	String[] questData = new String[4];

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		//setContentView(R.layout.result_questionnaire);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);
		
		//TextView resultado;
		//resultado = (TextView) findViewById(R.id.campoResultado);
		
		/*LayoutInflater layoutInflater = (LayoutInflater) 
        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
		layout.addView(1, layoutInflater.inflate(R.layout.navbar, this, false) ); */
		
		TextView resultado = new TextView(this);
		resultado.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		resultado.setText("testeee");
		layout.addView(resultado);
		
		Intent intent = getIntent();
		if (intent != null) {
			questData= (String[]) intent.getSerializableExtra("questionnaireData");
			
			System.out.println(questData[0] + questData[1] + questData[2] + questData[3]);
			resultado.setText(questData[0] +"\n" + questData[1] +"\n"+ questData[2] +"\n"+ questData[3]);
		}
		setContentView(layout);
	}

}
