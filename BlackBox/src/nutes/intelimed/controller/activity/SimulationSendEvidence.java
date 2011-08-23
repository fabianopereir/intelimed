package nutes.intelimed.controller.activity;

import java.util.ArrayList;

import nutes.intelimed.R;
import nutes.intelimed.model.EvidenceToServerScript;
import nutes.intelimed.model.IModelEvidenceToServer;
import nutes.intelimed.model.entity.EvidenceToServer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class SimulationSendEvidence extends Activity {


	private LinearLayout layout;

	private IModelEvidenceToServer daoEvidenceToServer;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);

		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);

		daoEvidenceToServer = (IModelEvidenceToServer) new EvidenceToServerScript(this);
		
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
		ArrayList<EvidenceToServer> arrayData = (ArrayList<EvidenceToServer>) daoEvidenceToServer.searchEvidenceToServer();
	
		String result = "";
	    if (arrayData.size() > 0) {
	        for (int i=0; i<arrayData.size(); i++) {
	            result = result + "\n" + arrayData.get(i).getFk_idno();
	        }
	    }
		
		Toast.makeText(SimulationSendEvidence.this, result, Toast.LENGTH_SHORT).show();
		
	}
}
