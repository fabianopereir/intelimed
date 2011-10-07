package nutes.intelimed.activity;

import nutes.intelimed.R;
import nutes.intelimed.model.evidence.Evidence;
import nutes.intelimed.model.evidence.EvidenceAnswers;
import nutes.intelimed.model.evidence.EvidenceAnswersDao;
import nutes.intelimed.model.evidence.EvidenceDao;
import nutes.intelimed.model.evidence.IModelEvidenceAnswersDao;
import nutes.intelimed.model.evidence.IModelEvidenceDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Classe responsável pela montagem da tela de resultado do questionário, com
 * suas questões e respectivas respostas obtidas através de questionnaireData de FormDiagnostic
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ResultDiagnostic extends Activity {
	protected static final String CATEGORIA = "nutes";

	private String result;
	private String[] answer;
	private String[] arrNO;

	private String medico;

	private IModelEvidenceDao daoEvidence;
	private IModelEvidenceAnswersDao daoEvidenceAnswer;
	private Evidence evidence;
	private EvidenceAnswers evidenceAnswer;
	
	private EditText justification;
	private TextView resultado;
	private Button validar, logout;
	private RadioGroup opiniaoMedico;

	
	@Override
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.result_questionnaire);

		resultado = (TextView) findViewById(R.id.result);
        validar = (Button) findViewById(R.id.btOkResult);
        opiniaoMedico = (RadioGroup) findViewById(R.id.opiniaoMedico);
        justification = (EditText) findViewById(R.id.justificativa);
        
		daoEvidence = (IModelEvidenceDao) new EvidenceDao(this);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersDao(this);

		evidence = new Evidence();
		evidenceAnswer = new EvidenceAnswers();
		
		
		Intent intent = getIntent();
		if (intent != null) {

			answer = (String[]) intent.getSerializableExtra("answer");
			arrNO = (String[]) intent.getSerializableExtra("no");
			result = (String) intent.getSerializableExtra("diagnostic");
			if (result != null) {

				evidence.setSistema(result);
				
				if(result.equals("No")){
					resultado.setText("O paciente não tem asma.");
				}else if(result.equals("Yes")){
					resultado.setText("O paciente tem asma.");
				}
			}
		}		

		validar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				storeEvidence(answer, arrNO, result);
			}
		});
		
		opiniaoMedico.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    		public void onCheckedChanged(RadioGroup group, int checkedId) {
    			boolean sim = R.id.concordo == checkedId;
    			boolean naoconcordo = R.id.naoconcordo == checkedId;
    			
    			if (sim) {
    				medico = "yes";
    				justification.setText("");
    				justification.setEnabled(false);
    			} else if(naoconcordo){
    				medico = "no";
    				justification.setEnabled(true);
    			}
    		}
    	});
		
		
		logout = (Button) findViewById(R.bt.btLogoff);
        
		/*
		back = (Button) findViewById(R.bt.btBack);
        back.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				daoEvidence.fechar();
				daoEvidenceAnswer.fechar();
				startActivity(new Intent(getBaseContext(), FormDiagnostic.class));
				
			}
		});*/
        logout.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				
				startActivity(new Intent(getBaseContext(), Login.class));
				finish();
				
			}
		});
    }	

	/**
	 * Método responsável por validar e mandar a classe EvidenceAnswersDao
	 * inserir a evidência no banco
	 * 
	 * @param answerData - array de string que armazena respostas
	 * @param noData - array de string que armazena informações de nós
	 * @param result - string com o resultado do sistema
	 * @return void
	 */
	public void storeEvidence(String[] answerData, String[] noData, String result) {
		evidence.setMedico(medico);
		
		evidence.setJustificativa(justification.getText().toString());

		Long idevidencia = daoEvidence.insertEvidence(evidence);

		if(evidence.getMedico()==null){	
            Toast.makeText(ResultDiagnostic.this, "Por favor responda se concorda com o diagnóstico.", Toast.LENGTH_SHORT).show();
		}else{
			for (int i = 0; i < answerData.length; i++) {
				if (noData[i] != null && answerData[i] != null) {
					evidenceAnswer.setFk_idevidencia(idevidencia);
	
					evidenceAnswer.setFk_idResposta(Long.parseLong(answerData[i]));
	
					daoEvidenceAnswer.insertEvidenceAnswers(evidenceAnswer);
				}
			}
			close();
			startActivity(new Intent(getBaseContext(), FormDiagnostic.class));
		}
	}
	
	/**
	 * 
	 * Implementação para botão voltar de Activity
	 * @param Indentificação de onclick
	 * @return value boolean
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        finish();
	        close();
	    	startActivity(new Intent(getBaseContext(), FormDiagnostic.class));
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	private void close() {
		daoEvidence.fechar();
		daoEvidenceAnswer.fechar();
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        setResult(RESULT_CANCELED);
        finish();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		close();
	}
}
