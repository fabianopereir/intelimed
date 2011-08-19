package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SimulationEvidencePrint extends Activity {
	private String evidenciaStr;
	private LinearLayout layout;


	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);
		
		Intent intent = getIntent();
		if (intent != null) {
			
			evidenciaStr = (String) intent.getSerializableExtra("evidencia");
		}
		
		setContentView(layout);

		TextView evidencia = null;
		evidencia.setText(evidenciaStr);
		layout.addView(evidencia);
	}
}
