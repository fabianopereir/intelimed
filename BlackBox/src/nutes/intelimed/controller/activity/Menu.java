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
	public void sendData()
	{
		dialog = ProgressDialog.show(this,"InteliMED", "Eviando dados", false,true);
		
		 ArrayList<EvidenceToServer> arrayData = (ArrayList<EvidenceToServer>) daoEvidenceToServer.searchEvidenceToServer();
		 int aux;
         int aux2 = 0;
         EvidenceToServer vAux;
         
		 JSONObject obj1 = new JSONObject();
		 JSONObject obj2;
		 JSONObject obj3;
		 JSONArray arr1 = new JSONArray();
		 JSONArray arr2 = new JSONArray();
		 try {
				
			/*
			 //Loop1
			 obj2 = new JSONObject();
			 obj2.put("idevidencia_respostas", 1);
			 obj2.put("idevidencia", 2);
			 obj2.put("sistema", "sim");
			 obj2.put("medico", "sim");
			 obj2.put("justificativa", "");
			 
			 //Loop 2
			 obj3 = new JSONObject();
			 obj3.put("fk_idno", 3);
			 obj3.put("idresposta", 4);
			 arr2.put(obj3);
			//End Loop 2
			 
			 obj2.put("respostas", arr2);
			 arr1.put(obj2);
			 
			//End Loop1
			 
			 obj1.put("dados", arr1);
			 
			 */
			 for (int i = 0; i < arrayData.size(); i++) {
	             aux = i;
	             vAux = arrayData.get(aux);
	             
	             obj2 = new JSONObject();
				 
				 obj2.put("idevidencia", arrayData.get(i).getIdevidencia());
				 obj2.put("sistema", arrayData.get(i).getSistema());
				 obj2.put("medico", arrayData.get(i).getMedico());
				 obj2.put("justificativa", arrayData.get(i).getJustificativa());
				 
	             while (arrayData.get(i).getIdevidencia() == vAux.getIdevidencia() && aux < arrayData.size()) {
	                     
		            	 obj3 = new JSONObject();
		    			 obj3.put("fk_idno", arrayData.get(aux).getFk_idno());
		    			 obj3.put("idresposta", arrayData.get(aux).getIdresposta());
		    			 arr2.put(obj3);
		    			
	                     aux++;
	                     if (aux < arrayData.size()) {
	                             vAux = arrayData.get(aux);
	                     }
	             }
	             obj2.put("respostas", arr2);
				 arr1.put(obj2);
	             aux2++;
	            
	             i = aux - 1;
	         }
			 obj1.put("dados", arr1);
			 System.out.println(obj1);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
        
         /*int aux;
         int aux2 = 0;
         EvidenceToServer vAux;
         for (int i = 0; i < arrayData.size(); i++) {
             aux = i;
             vAux = arrayData.get(aux);
             System.out.println("IdEvidence: "+arrayData.get(i).getIdevidencia());
             System.out.println("Sistema: "+arrayData.get(i).getSistema());
             System.out.println("Medico: "+arrayData.get(i).getMedico());
             System.out.println("Justificativa: "+arrayData.get(i).getJustificativa());
             while (arrayData.get(i).getIdevidencia() == vAux.getIdevidencia() && aux < arrayData.size()) {
                     
                     System.out.println("IdNO: "+arrayData.get(aux).getFk_idno());
                     System.out.println("IdResposta: "+arrayData.get(aux).getIdresposta());
                     aux++;
                     if (aux < arrayData.size()) {
                             vAux = arrayData.get(aux);
                     }
             }
             aux2++;
            
             i = aux - 1;
         }*/
             
             
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
		finish();
	}
}
