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
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class CadastroPaciente extends Activity implements OnClickListener{

	public static InterfaceModelPaciente dao;
	
	EditText nome, dtnascimento;
	Button cadPaciente;
	RadioButton delPaciente,edPaciente;
	RadioGroup radioGroup;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        dao = new PacienteScript(this);
        setContentView(R.layout.paciente);
        nome = (EditText) findViewById(R.paciente.nome);
        dtnascimento = (EditText) findViewById(R.paciente.dtnascimento);
        cadPaciente = (Button) findViewById(R.bt.cadPaciente);
        cadPaciente.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        delPaciente = (RadioButton) findViewById(R.id.remover);
        edPaciente = (RadioButton) findViewById(R.id.editar);
        radioGroup.setVisibility(RadioGroup.GONE);
        delPaciente.setVisibility(RadioButton.GONE);
        edPaciente.setVisibility(RadioButton.GONE);
        
	}
	@Override
	public void onClick(View v) {
		save();
	}	
	public void save()
	{
		Paciente paciente = new Paciente();
		
		paciente.nome = nome.getText().toString();
		paciente.datanascimento = dtnascimento.getText().toString();
		salvarPaciente(paciente);
	
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}	
	public void salvarPaciente(Paciente paciente)
	{
		dao.salvar(paciente);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Fecha o banco
		dao.fechar();
	}
}