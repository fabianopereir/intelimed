package nutes.intelimed.controller.activity;


import nutes.intelimed.model.EvidenceAnswersScript;
import nutes.intelimed.model.EvidenceScript;
import nutes.intelimed.model.IModelEvidence;
import nutes.intelimed.model.IModelEvidenceAnswers;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.EvidenceAnswers;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe respons�vel pela montagem da tela de resultado do
 *              question�rio, com suas quest�es e respectivas respostas obtidas
 *              atrav�s de questionnaireData da Activity DiagnosticForm
 */
public class DiagnosticResult extends Activity implements OnCheckedChangeListener {

	private String result;
	private String[] answer;
	private String[] arrNO;
	
	private String medico;
	
	private IModelEvidence daoEvidence;
	private IModelEvidenceAnswers daoEvidenceAnswer;
	
	private Evidence evidence;
	private EvidenceAnswers evidenceAnswer;
	private EditText justification;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);
		
		daoEvidence = (IModelEvidence) new EvidenceScript(this);
		daoEvidenceAnswer = (IModelEvidenceAnswers) new EvidenceAnswersScript(this);
		
		evidence = new Evidence();
		evidenceAnswer = new EvidenceAnswers();
		
		Intent intent = getIntent();
		if (intent != null) {
			
			answer = (String[]) intent.getSerializableExtra("answer");
			arrNO = (String[]) intent.getSerializableExtra("no");
			result = (String) intent.getSerializableExtra("diagnostic");
			if (result != null) {
			
				evidence.setSistema(result);
				
				TextView resultado = new TextView(this);
				resultado.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				resultado.setText(result);
				layout.addView(resultado);
			}

		}
		
	    layout.addView(createRadioButton());

	    justification = new EditText(this);
	    justification.setLines(5);
	    justification.setWidth(250);
	    justification.setGravity(48);
	    justification.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		layout.addView(justification);
	    
		Button validar = new Button(this);
		validar.setText("OK");
		layout.addView(validar);
		validar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				validar(answer, arrNO, result);
			}
		});
		setContentView(layout);
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId==1) medico = "yes"; 
			else medico = "no"; 
			
		Toast.makeText(DiagnosticResult.this, Integer.toString(checkedId), Toast.LENGTH_LONG).show();
		
	}
	
	/**
	 * @Description M�todo respons�vel por criar dinamicamente radio group da tela de resultado
	 * @return RadioGroup
	 */
	private RadioGroup createRadioButton() {
		
		RadioGroup radio_group = new RadioGroup (this);
		
		RadioButton radio_button1, radio_button2;
		
		radio_button1 = new RadioButton (this);
		radio_button2 = new RadioButton (this);
        
		radio_button1.setId (1);
        radio_button1.setTag("yes");
        radio_button1.setText("Concordo");
        
        radio_group.addView (radio_button1);
        
        radio_button2.setId (2);
        radio_button2.setTag("no");
        radio_button2.setText("N�o Concordo");
        radio_group.addView (radio_button2);
        radio_group.setOnCheckedChangeListener (this);
	    return radio_group;
	}
	
	public void validar(String[] answerData, String[] noData, String result){
		Toast.makeText(DiagnosticResult.this, answerData.length + " :: " + noData.length, Toast.LENGTH_LONG).show();
		evidence.setMedico(medico);
		evidence.setJustificativa(justification.getText().toString());
		
		Long idevidencia = daoEvidence.insertEvidence(evidence);
		
		for (int i=0; i<answerData.length; i++)
		{
			if (noData[i]!=null && answerData[i]!=null)
			{
				
				evidenceAnswer.setFk_idevidencia(idevidencia);
				evidenceAnswer.setFk_idno(Long.parseLong(noData[i]));
				evidenceAnswer.setFk_idResposta(Long.parseLong(answerData[i]));

				daoEvidenceAnswer.insertEvidenceAnswers(evidenceAnswer);
				Log.i("jamilson", "NO: " + noData[i]+ " Resposta: "+ answerData[i]);
			}
				
		}
		
		
	}

}
