package nutes.intelimed.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DiagnosticResult extends Activity {
	String[] questData = new String[4];
	TextView resultado;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		
		setContentView(R.layout.questionnaire_asma);
		

		Intent intent = getIntent();
		if (intent != null) {
			questData = (String[]) intent
					.getSerializableExtra("questionnaireData");
			Toast.makeText(
					DiagnosticResult.this,
					questData[0] + questData[1] + questData[2]+ questData[3], Toast.LENGTH_LONG).show();
		
				
		}
	}
}
