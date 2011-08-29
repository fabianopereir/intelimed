package nutes.intelimed.controller.activity;

import java.util.ArrayList;

import nutes.intelimed.Login;
import nutes.intelimed.R;
import nutes.intelimed.model.EvidenceToServerScript;
import nutes.intelimed.model.IModelEvidenceToServer;
import nutes.intelimed.model.entity.EvidenceToServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * Classe responsável por apresentar a tela de menu (com duas opções: diagnóstico e sincronizar dados) ao usuário 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Menu extends Activity{
	private ProgressDialog dialog;
	
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
				sendData();
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
	 * Evento disparado quando o botão de "sincronizar dados" da Activity Menu recebe um click
	 * @return void
	 */
	public void sendData()
	{
		dialog = ProgressDialog.show(this,"InteliMED", "Enviando dados", false,true);
		
		 ArrayList<EvidenceToServer> arrayData = (ArrayList<EvidenceToServer>) daoEvidenceToServer.searchEvidenceToServer();
		 int aux;
         int aux2 = 0;
         EvidenceToServer vAux;
         
		 JSONObject data = new JSONObject();
		 JSONObject dataEvidence;
		 JSONObject dataAnswer;
		 JSONArray arrData = new JSONArray();
		 JSONArray arrAnswer;
		 try {

			 for (int i = 0; i < arrayData.size(); i++) {
	             aux = i;
	             vAux = arrayData.get(aux);
	             
	             arrAnswer = new JSONArray();
	             dataEvidence = new JSONObject();
				 
				 dataEvidence.put("idevidencia", arrayData.get(i).getIdevidencia());
				 dataEvidence.put("sistema", arrayData.get(i).getSistema());
				 dataEvidence.put("medico", arrayData.get(i).getMedico());
				 dataEvidence.put("justificativa", arrayData.get(i).getJustificativa());
				 
	             while (arrayData.get(i).getIdevidencia() == vAux.getIdevidencia() && aux < arrayData.size()) {
	                     
		            	 dataAnswer = new JSONObject();
		    			 dataAnswer.put("fk_idno", arrayData.get(aux).getFk_idno());
		    			 dataAnswer.put("idresposta", arrayData.get(aux).getIdresposta());
		    			 arrAnswer.put(dataAnswer);
		    			
	                     aux++;
	                     if (aux < arrayData.size()) {
	                             vAux = arrayData.get(aux);
	                     }
	             }
	             dataEvidence.put("respostas", arrAnswer);
				 arrData.put(dataEvidence);
	             aux2++;
	            
	             i = aux - 1;
	         }
			 data.put("dados", arrData);
			 System.out.println(data);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dialog.dismiss();
		}
             
	}
	
	/**
	 * 
	 * Implementação para botão voltar de Activity
	 * @param Indentificação de onclick
	 * @return boolean
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        	//finish();
	    	startActivity(new Intent(getBaseContext(), Login.class));
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// Fecha a tela
		//finish();
	}
}
