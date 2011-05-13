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
	Button upPaciente;
	Paciente paciente;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dao = new PacienteScript(this);
		setContentView(R.layout.paciente);
		nome = (EditText) findViewById(R.paciente.nome);
        dtnascimento = (EditText) findViewById(R.paciente.dtnascimento);
        upPaciente = (Button) findViewById(R.bt.cadPaciente);
        upPaciente.setText("Atualizar");
        upPaciente.setOnClickListener(this);
        
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
		dao.atualizar(paciente);
		setResult(RESULT_OK, new Intent());
		// Fecha a tela
		finish();
	}

}
