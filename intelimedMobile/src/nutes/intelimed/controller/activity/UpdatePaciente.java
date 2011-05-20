package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import nutes.intelimed.model.InterfaceModelPaciente;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UpdatePaciente extends Activity implements OnClickListener{
	public static InterfaceModelPaciente dao;
	EditText nome, dtnascimento;
	Button upPaciente,delPaciente;
	Paciente paciente;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dao = new PacienteScript(this);
		setContentView(R.layout.paciente);
		nome = (EditText) findViewById(R.paciente.nome);
        dtnascimento = (EditText) findViewById(R.paciente.dtnascimento);
        upPaciente = (Button) findViewById(R.bt.cadPaciente);
        //delPaciente= (Button) findViewById(R.bt.delPaciente);
        upPaciente.setText("Atualizar");
        upPaciente.setOnClickListener(this);
        
        //exibe os dados do paciente
		Intent intent = getIntent();
        if (intent!=null)
        {
        	paciente = (Paciente) intent.getSerializableExtra("paciente");
        	//String algumaString = intent.getString("nome");
        	nome.setText(paciente.nome);
        	dtnascimento.setText(paciente.datanascimento);
        }
	}
	
	@Override
	public void onClick(View v) {
		update();
	}
	
	public void update(){
		paciente.nome = nome.getText().toString();
		paciente.datanascimento = dtnascimento.getText().toString();
		dao.atualizar(paciente);
		setResult(RESULT_OK, new Intent());
		// Fecha a tela
		finish();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada na tela pendente
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}

}
