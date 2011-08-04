package nutes.intelimed.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe responsável pela montagem da tela de resultado do questionário, 
 *  	com suas questões e respectivas respostas obtidas através de questionnaireData da Activity DiagnosticForm
 */
public class DiagnosticResult extends Activity {

	private String[] questData;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);
		
		Intent intent = getIntent();
		if (intent != null) {
			
			questData = (String[]) intent.getSerializableExtra("questionnaireData");
			
			for (int i=0; i<questData.length; i++)
			{
				if (questData[i]!=null)
				{
					TextView resultado = new TextView(this);
					resultado.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					resultado.setText(questData[i] +"\n");
					layout.addView(resultado);
				}
					
			}
			
		}
		setContentView(layout);
	}

}
