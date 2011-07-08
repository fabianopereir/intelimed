package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ResultQuestionnaire extends Activity {
	String[] questData = new String[4];
	TextView resultado;
	ImageButton back, logout;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.result_questionnaire);

		resultado = (TextView) findViewById(R.id.Resultado);
		
		back = (ImageButton) findViewById(R.bt.btBack);
		logout = (ImageButton) findViewById(R.bt.btLogoff);

		Intent intent = getIntent();
		if (intent != null) {
			questData = (String[]) intent
					.getSerializableExtra("questionnaireData");
			Toast.makeText(
					ResultQuestionnaire.this,
					questData[0] + questData[1] + questData[2]+ questData[3], Toast.LENGTH_LONG).show();
					resultado.setText(questData[0] + questData[1] + questData[2]+ questData[3]);
			
		
		}
		
		 back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//finish();
					startActivity(new Intent(getBaseContext(),QuestionnaireAsma.class));
					
				}
			});
			
			logout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getBaseContext(), Login.class));
					
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
	    	startActivity(new Intent(getBaseContext(), QuestionnaireAsma.class));
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
