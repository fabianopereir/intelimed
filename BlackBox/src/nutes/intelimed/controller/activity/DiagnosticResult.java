package nutes.intelimed.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe responsável pela montagem da tela de resultado do
 *              questionário, com suas questões e respectivas respostas obtidas
 *              através de questionnaireData da Activity DiagnosticForm
 */
public class DiagnosticResult extends Activity {

	private String questData;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);

		Intent intent = getIntent();
		if (intent != null) {

			questData = (String) intent
					.getSerializableExtra("questionnaireData");

			if (questData != null) {
				TextView resultado = new TextView(this);
				resultado.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				resultado.setText(questData);
				layout.addView(resultado);
			}

		}
		
	    layout.addView(createRadioButton());

	    EditText justificativa = new EditText(this);
	    justificativa.setLines(5);
	    //justificativa.setGravity(TOP);
	    
	    justificativa.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    
		layout.addView(justificativa);
	    
		setContentView(layout);
	}

	/**
	 * @Description Método responsável por criar dinamicamente radio group da tela de resultado
	 * @return RadioGroup
	 */
	private RadioGroup createRadioButton() {
	    final RadioButton[] rb = new RadioButton[2];
	    RadioGroup rg = new RadioGroup(this); //create the RadioGroup
	    rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
	    for(int i=0; i<2; i++){
	        rb[i]  = new RadioButton(this);
	        rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
	    }
	    rb[0].setText("Não Concordo");
	    rb[1].setText("Concordo");
	    return rg;
	}

}
