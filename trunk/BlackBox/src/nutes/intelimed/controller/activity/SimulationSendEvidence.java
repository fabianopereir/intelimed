package nutes.intelimed.controller.activity;

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

public class SimulationSendEvidence extends Activity {

	private EvidenceToServer evidence;

	private IModelEvidenceToServer daoEvidenceToServer;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.setBackgroundResource(R.drawable.gradientbg);

		daoEvidenceToServer = (IModelEvidenceToServer) new EvidenceToServerScript(
				this);

		evidence = new EvidenceToServer();

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

	public void validar() {

	}
}
