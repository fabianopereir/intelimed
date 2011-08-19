package nutes.intelimed.controller.activity;

import java.util.ArrayList;

import nutes.intelimed.R;
import nutes.intelimed.controller.util.AnswerOption;
import nutes.intelimed.model.EvidenceToServerScript;
import nutes.intelimed.model.IModelEvidenceToServer;
import nutes.intelimed.model.entity.Evidence;
import nutes.intelimed.model.entity.EvidenceAnswers;
import nutes.intelimed.model.entity.EvidenceToServer;
import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.service.BlackBox;
import nutes.intelimed.service.IBlackBox;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SimulationSendEvidence extends Activity {

	private Evidence evidence;
	private EvidenceAnswers evidenceAnswers;
	private LinearLayout layout;

	private IModelEvidenceToServer daoEvidenceToServer;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);

		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);

		daoEvidenceToServer = (IModelEvidenceToServer) new EvidenceToServerScript(
				this);

		evidence = new Evidence();

		Button validar = new Button(this);
		validar.setText("OK");
		layout.addView(validar);
		validar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				validar();
			}
		});
		setContentView(layout);
	}

	/**
	 * Método responsável por preparar o conteúdo a ser enviado ao servidor, 
	 * chamado quando o botão "OK" é clicado
	 * @return void
	 */
	public void validar() {
		ArrayList<Object> arrayData = (ArrayList<Object>) daoEvidenceToServer.searchEvidenceToServer();
	
		String result = "";
	    if (arrayData.size() > 0) {
	        for (int i=0; i<arrayData.size(); i++) {
	            result = result + "\n" + arrayData.get(i);
	        }
	    }
		
		Toast.makeText(SimulationSendEvidence.this, result, Toast.LENGTH_SHORT).show();
		
		Intent it = new Intent(getBaseContext(), SimulationEvidencePrint.class);
		it.putExtra("evidencia",result);
		startActivity(it);
	    
	}
}
