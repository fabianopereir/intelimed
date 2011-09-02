package nutes.intelimed.controller.activity;

import nutes.intelimed.Login;
import nutes.intelimed.R;
import nutes.intelimed.model.EvidenceAnswersScript;
import nutes.intelimed.model.EvidenceScript;
import nutes.intelimed.model.DAO.IModelEvidenceAnswersDao;
import nutes.intelimed.model.DAO.IModelEvidenceDao;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.EvidenceAnswers;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Classe respons�vel pela montagem da tela de resultado do question�rio, com
 * suas quest�es e respectivas respostas obtidas atrav�s de questionnaireData de FormDiagnostic
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ResultDiagnostic extends Activity {

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
	private Button validar;
	private RadioGroup opiniaoMedico;

	private ImageButton back,logout;
	
	@Override
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.result_questionnaire);

		resultado = (TextView) findViewById(R.id.result);
        validar = (Button) findViewById(R.id.btOkResult);
        opiniaoMedico = (RadioGroup) findViewById(R.id.opiniaoMedico);
        justification = (EditText) findViewById(R.id.justificativa);
        
		daoEvidence = (IModelEvidenceDao) new EvidenceScript(this);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersScript(this);

		evidence = new Evidence();
		evidenceAnswer = new EvidenceAnswers();
		
		
		Intent intent = getIntent();
		if (intent != null) {

			answer = (String[]) intent.getSerializableExtra("answer");
			arrNO = (String[]) intent.getSerializableExtra("no");
			result = (String) intent.getSerializableExtra("diagnostic");
			if (result != null) {

				evidence.setSistema(result);

				if(result.equals("NO")){
					resultado.setText("O paciente n�o tem asma.");
				}else if(result.equals("YES")){
					resultado.setText("O paciente tem asma.");
				}
			}
		}		

		validar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				validar(answer, arrNO, result);
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
		
		back = (ImageButton) findViewById(R.bt.btBack);
		logout = (ImageButton) findViewById(R.bt.btLogoff);
        
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getBaseContext(), FormDiagnostic.class));
				
			}
		});
        logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getBaseContext(), Login.class));
				finish();
				
			}
		});
    }	

	/**
	 * M�todo respons�vel por validar e mandar a classe EvidenceAnswersDao
	 * inserir a evid�ncia no banco
	 * 
	 * @param answerData - array de string que armazena respostas
	 * @param noData - array de string que armazena informa��es de n�s
	 * @param result - string com o resultado do sistema
	 * @return void
	 */
	public void validar(String[] answerData, String[] noData, String result) {
		evidence.setMedico(medico);
		
		evidence.setJustificativa(justification.getText().toString());

		Long idevidencia = daoEvidence.insertEvidence(evidence);

		if(evidence.getMedico()==null){	
            Toast.makeText(ResultDiagnostic.this, "Por favor responda se concorda com o diagn�stico.", Toast.LENGTH_SHORT).show();
		}else{
			for (int i = 0; i < answerData.length; i++) {
				if (noData[i] != null && answerData[i] != null) {
					evidenceAnswer.setFk_idevidencia(idevidencia);
	
					evidenceAnswer.setFk_idResposta(Long.parseLong(answerData[i]));
	
					daoEvidenceAnswer.insertEvidenceAnswers(evidenceAnswer);
				}
			}
			startActivity(new Intent(getBaseContext(), FormDiagnostic.class));
		}
	}
	
	/**
	 * 
	 * Implementa��o para bot�o voltar de Activity
	 * @param Indentifica��o de onclick
	 * @return value boolean
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        finish();
	    	startActivity(new Intent(getBaseContext(), FormDiagnostic.class));
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        setResult(RESULT_CANCELED);
        finish();
    }

	
}