package nutes.intelimed.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nutes.intelimed.R;
import nutes.intelimed.communication.ServerConstants;
import nutes.intelimed.controller.ReceiveTree;
import nutes.intelimed.controller.SendEvidence;
import nutes.intelimed.controller.TreeUpdate;
import nutes.intelimed.model.evidence.EvidenceServer;
import nutes.intelimed.model.evidence.EvidenceServerDao;
import nutes.intelimed.model.evidence.IModelEvidenceServerDao;

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


/**
 * Classe responsável por apresentar a tela de menu (com duas opções: diagnóstico e sincronizar dados) ao usuário 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Menu extends Activity{
	
	private ProgressDialog dialog;
		
	private Button logout, diagnostic, sync, TreeUp; 
	
	protected static final String CATEGORIA = "nutes";
	
    private IModelEvidenceServerDao daoEvidenceToServer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
        diagnostic = (Button) findViewById(R.id.btDiagnostico);
        sync = (Button) findViewById(R.id.btSincronizarDados);
        TreeUp = (Button) findViewById(R.id.btAtualizarArvore);
        
        
        logout = (Button) findViewById(R.bt.btLogoff);
        
        //back = (Button) findViewById(R.bt.btBack);
        //back.setVisibility(Button.INVISIBLE);
       
        
        daoEvidenceToServer = (IModelEvidenceServerDao) new EvidenceServerDao(this);

        
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
			
			
			public void onClick(View v) {
				dialog = ProgressDialog.show(Menu.this,"InteliMED", "Atualizando árvore...", false,true);
				treeUpdate();
			}
		});
		
		logout.setOnClickListener(new OnClickListener() {
			
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
	            
	             i = aux - 1;
	         }
			 data.put("dados", arrData);
			 System.out.println("Dados Mobile: "+data);
			 Map params = new HashMap();
			 params.put("n1", arrData);
			 
			 SendEvidence sEv = new SendEvidence(getBaseContext());
			 sEv.setUrl(ServerConstants.getContextFromPost());
			 sEv.setParams(params);
			 sEv.start();
	
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			dialog.dismiss();
		}
             
	}
	
	private void treeUpdate(){
		TreeUpdate tree = new TreeUpdate(getBaseContext());
		try {
			ReceiveTree tUP = new ReceiveTree(tree);
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
	        daoEvidenceToServer.fechar();
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Fecha o banco
		daoEvidenceToServer.fechar();
	}
}
