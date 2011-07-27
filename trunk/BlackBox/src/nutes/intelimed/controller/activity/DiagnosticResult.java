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

	String[] questData;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);
		
		/*TextView resultado = new TextView(this);
		resultado.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		layout.addView(resultado);
		*/
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
