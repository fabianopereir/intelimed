package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ResultQuestionnaire extends Activity {
	String[] questData = new String[4];
	
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		setContentView(R.layout.result_questionnaire);

		Intent intent = getIntent();
		if (intent != null) {
			questData = (String[]) intent.getSerializableExtra("questionnaireData");
			Toast.makeText(ResultQuestionnaire.this,"Q1: " +questData[0]+"--"+ "Q2: " +questData[1]+"--"+ "Q3: " +questData[2], Toast.LENGTH_LONG).show();
		}
	}
}
