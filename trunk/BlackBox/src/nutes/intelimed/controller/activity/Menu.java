package nutes.intelimed.controller.activity;

import java.util.ArrayList;

import nutes.intelimed.Login;
import nutes.intelimed.R;
import nutes.intelimed.model.EvidenceToServerScript;
import nutes.intelimed.model.IModelEvidenceToServer;
import nutes.intelimed.model.entity.EvidenceToServer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Classe responsável por apresentar a tela de menu (com duas opções: diagnóstico e sincronizar dados) ao usuário 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Menu extends Activity{
	private ImageButton back, logout;
	
    private IModelEvidenceToServer daoEvidenceToServer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
        final Button diagnostic = (Button) findViewById(R.id.btDiagnostico);
        final Button sync = (Button) findViewById(R.id.btSincronizarDados);

        
        back = (ImageButton) findViewById(R.bt.btBack);
        back.setVisibility(ImageButton.GONE);
        logout = (ImageButton) findViewById(R.bt.btLogoff);
        
        daoEvidenceToServer = (IModelEvidenceToServer) new EvidenceToServerScript(this);

        
		diagnostic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent it = new Intent(getBaseContext(), FormDiagnostic.class);
				startActivity(it);
			}
		});
		
		sync.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
	            ArrayList<EvidenceToServer> arrayData = (ArrayList<EvidenceToServer>) daoEvidenceToServer.searchEvidenceToServer();
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
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// Fecha a tela
		finish();
	}
}
