package nutes.intelimed.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela montagem da tela de questionário
 */
public class DiagnosticResult extends Activity {

	String[] questData = new String[4];
	

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.result_questionnaire);
		
		TextView resultado;
		resultado = (TextView) findViewById(R.id.campoResultado);

		Intent intent = getIntent();
		if (intent != null) {
			questData= (String[]) intent.getSerializableExtra("questionnaireData");
			
			/*Toast.makeText(DiagnosticResult.this,
					questData[0] + questData[1] + questData[2] + questData[3],
					Toast.LENGTH_LONG).show();*/
			System.out.println(questData[0] + questData[1] + questData[2] + questData[3]);
			resultado.setText(questData[0] +"\n" + questData[1] +"\n"+ questData[2] +"\n"+ questData[3]);
		}
	}

}
