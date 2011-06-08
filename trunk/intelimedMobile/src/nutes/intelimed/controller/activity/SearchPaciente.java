package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import nutes.intelimed.model.InterfaceModelPaciente;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SearchPaciente extends Activity{
	public static InterfaceModelPaciente dao;
	EditText nome, datanascimento;
	ImageButton btPesquisar,back, logout;
	
	public void onCreate(Bundle iclice){
		super.onCreate(iclice);
		
		dao = new PacienteScript(this);
		setContentView(R.layout.searchpaciente);
		
		back = (ImageButton) findViewById(R.bt.btBack);
		logout = (ImageButton) findViewById(R.bt.btLogoff);
		btPesquisar = (ImageButton) findViewById(R.bt.btSearch);
		nome = (EditText) findViewById(R.search.campoNome);
		datanascimento = (EditText) findViewById(R.search.campoDatanascimento);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(getBaseContext(), MenuPaciente.class));
				//finish();
				
			}
		});
		
		btPesquisar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Search();
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
	
	
	
	public void Search() {
		Paciente paciente = searchPaciente(nome.getText().toString());
		if(paciente!=null){
			Intent intent = new Intent();
			//intent.setClassName(<pacote>, <<br.nutes>.noma da classe>)
			intent.setClassName(this, "nutes.intelimed.controller.activity.UpdatePaciente");
			intent.putExtra("paciente", paciente);
			//intent.putExtra("algumaString", algumaString);
			
			startActivity(intent);
		}else
		{
			Toast.makeText(SearchPaciente.this, "Nenhum Paciente encontrado", Toast.LENGTH_SHORT).show();
		}
	}
	public Paciente searchPaciente(String nomePaciente){
		Log.i("jamilson", "Nome do paciente"+nomePaciente);
		Paciente paciente = dao.buscarPacientePorNome(nomePaciente);
		return paciente;
	}
	
	/**
	 * @author jamilson
	 * @Description Implementation for button  back of Activity
	 * @param Indentification of onclick for mouse
	 * @return value boolean
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	startActivity(new Intent(getBaseContext(), MenuPaciente.class));
	        	//finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada pendente na tela
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}
	
	
}
