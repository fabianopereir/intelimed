package nutes.intelimed.controller.activity;

import nutes.intelimed.Login;
import nutes.intelimed.R;
import nutes.intelimed.model.EvidenceAnswersScript;
import nutes.intelimed.model.EvidenceScript;
import nutes.intelimed.model.IModelEvidence;
import nutes.intelimed.model.IModelEvidenceAnswers;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.EvidenceAnswers;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * Classe responsável pela montagem da tela de resultado do questionário, com
 * suas questões e respectivas respostas obtidas através de questionnaireData da
 * Activity FormDiagnostic
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ResultDiagnostic extends Activity implements OnCheckedChangeListener {

	private String result;
	private String[] answer;
	private String[] arrNO;

	private String medico;

	private IModelEvidence daoEvidence;
	private IModelEvidenceAnswers daoEvidenceAnswer;

	private Evidence evidence;
	private EvidenceAnswers evidenceAnswer;
	private EditText justification;

	private LinearLayout layout;
	private ImageButton back,logout;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.questionnaire_asma);

		layout = (LinearLayout) findViewById(R.id.LinearLayout02);

		daoEvidence = (IModelEvidence) new EvidenceScript(this);
		daoEvidenceAnswer = (IModelEvidenceAnswers) new EvidenceAnswersScript(
				this);

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
				resultado.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				resultado.setText("Resultado: " + result);
				layout.addView(resultado);
			}
		}

		layout.addView(createRadioButton());

		justification = new EditText(this);
		justification.setLines(5);
		justification.setGravity(48);
		justification.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		layout.addView(justification);

		Button validar = new Button(this);
		validar.setText("OK");
		layout.addView(validar);
		validar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				validar(answer, arrNO, result);
			}
		});
		
		back = (ImageButton) findViewById(R.bt.btBack);
		logout = (ImageButton) findViewById(R.bt.btLogoff);
        
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getBaseContext(), FormDiagnostic.class));
				//	finish();
				
			}
		});
        logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getBaseContext(), Login.class));
				//	finish();
				
			}
		});

	}

	/**
	 * O método é chamado ao clicar em uma opção do radio group
	 * 
	 * @param group - instância do RadioGroup
	 * @param checkedId - índice da resposta selecionada
	 * @return void
	 */
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == 1) {
			medico = "yes";
			justification.setEnabled(false);
		} else {
			medico = "no";
			justification.setEnabled(true);
		}
	}

	/**
	 * Método responsável por criar radio group da tela de resultado
	 * 
	 * @return RadioGroup com 2 radioButtons criados dinamicamente
	 */
	private RadioGroup createRadioButton() {

		RadioGroup radio_group = new RadioGroup(this);

		RadioButton radio_button1, radio_button2;

		radio_button1 = new RadioButton(this);
		radio_button2 = new RadioButton(this);

		radio_button1.setId(1);
		radio_button1.setTag("yes");
		radio_button1.setText("Concordo");

		radio_group.addView(radio_button1);

		radio_button2.setId(2);
		radio_button2.setTag("no");
		radio_button2.setText("Não Concordo");
		radio_group.addView(radio_button2);
		radio_group.setOnCheckedChangeListener(this);

		return radio_group;
	}

	/**
	 * Método responsável por validar e mandar a classe EvidenceAnswersDao
	 * inserir a evidência no banco
	 * 
	 * @param answerData
	 * @param noData
	 * @param result
	 * @return void
	 */
	public void validar(String[] answerData, String[] noData, String result) {
		evidence.setMedico(medico);
		evidence.setJustificativa(justification.getText().toString());

		Long idevidencia = daoEvidence.insertEvidence(evidence);

		for (int i = 0; i < answerData.length; i++) {
			if (noData[i] != null && answerData[i] != null) {
				evidenceAnswer.setFk_idevidencia(idevidencia);

				evidenceAnswer.setFk_idResposta(Long.parseLong(answerData[i]));

				daoEvidenceAnswer.insertEvidenceAnswers(evidenceAnswer);
			}
		}

	}

}
