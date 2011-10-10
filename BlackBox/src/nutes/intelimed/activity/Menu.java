package nutes.intelimed.activity;

import nutes.intelimed.R;
import nutes.intelimed.controller.evidence.EvidenceController;
import nutes.intelimed.controller.evidence.IEvidence;
import nutes.intelimed.controller.tree.ITree;
import nutes.intelimed.controller.tree.TreeController;
import org.json.JSONException;

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
		try {
			IEvidence evidence = new EvidenceController(this);
			evidence.sendEvidence();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			dialog.dismiss();
		}
             
	}
	
	private void treeUpdate(){
		dialog = ProgressDialog.show(Menu.this,"InteliMED", "Atualizando árvore...", false,true);
		try {
			ITree tree = new TreeController(getBaseContext());
			tree.receiveTree();
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
