package nutes.intelimed.controller.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nutes.intelimed.Login;
import nutes.intelimed.ParserSimulation;
import nutes.intelimed.R;
import nutes.intelimed.communication.SendEvidence;
import nutes.intelimed.communication.ServerConstants;
import nutes.intelimed.communication.ReceiveTree;
import nutes.intelimed.model.EvidenceServerScript;
import nutes.intelimed.model.DAO.IModelEvidenceServerDao;
import nutes.intelimed.model.entity.EvidenceServer;
import nutes.intelimed.service.BlackBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
	
	private Button diagnostic, sync, TreeUp; 
	
	protected static final String CATEGORIA = "nutes";
	
    private IModelEvidenceServerDao daoEvidenceToServer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
        diagnostic = (Button) findViewById(R.id.btDiagnostico);
        sync = (Button) findViewById(R.id.btSincronizarDados);
        TreeUp = (Button) findViewById(R.id.btAtualizarArvore);
        
        back = (ImageButton) findViewById(R.bt.btBack);
        logout = (ImageButton) findViewById(R.bt.btLogoff);
        
        back.setVisibility(ImageButton.GONE);
       
        
        daoEvidenceToServer = (IModelEvidenceServerDao) new EvidenceServerScript(this);

        
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
		
		TreeUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog = ProgressDialog.show(Menu.this,"InteliMED", "Atualizando árvore...", false,true);
				TreeUpdate();
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
	 * Evento disparado quando o botão de "sincronizar dados" da Activity Menu recebe um click
	 * @return void
	 */
	public void sendData(){
		dialog = ProgressDialog.show(this,"InteliMED", "Enviando dados", false,true);
		
		 ArrayList<EvidenceServer> arrayData = (ArrayList<EvidenceServer>) daoEvidenceToServer.searchEvidenceToServer();
		 int aux;
         int aux2 = 0;
         EvidenceServer vAux;
         
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
			 System.out.println("Dados Mobile: "+data);
			 Map params = new HashMap();
			 params.put("n1", arrData);
			 
			 SendEvidence sEv = new SendEvidence(getBaseContext());
			 sEv.url = ServerConstants.getContextFromPost();
			 sEv.params = params;
			 sEv.start();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally {
			dialog.dismiss();
		}
             
	}
	
	private void TreeUpdate(){
		try {
			BlackBox bb = new BlackBox(getBaseContext());
			ReceiveTree tUP = new ReceiveTree(bb);
			tUP.start();
			
		}catch (Exception e) {
			Log.i(CATEGORIA, e.getMessage(),e);
		}finally{
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
	        finish();
	    	startActivity(new Intent(getBaseContext(), Login.class));
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
