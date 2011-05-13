package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import nutes.intelimed.model.InterfaceModelPaciente;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SearchPaciente extends Activity implements OnClickListener{
	public static InterfaceModelPaciente dao;
	EditText nome, datanascimento;
	ImageButton btPesquisar;
	public void onCreate(Bundle iclice){
		super.onCreate(iclice);
		dao = new PacienteScript(this);
		setContentView(R.layout.searchpaciente);
		nome = (EditText) findViewById(R.search.campoNome);
		datanascimento = (EditText) findViewById(R.search.campoDatanascimento);
		btPesquisar = (ImageButton) findViewById(R.bt.btSearch);
		btPesquisar.setOnClickListener(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada pendente na tela
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
	

}
