package nutes.intelimed.controller.activity;


import nutes.intelimed.R;
import nutes.intelimed.model.InterfaceModelPaciente;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class CadastroPaciente extends Activity{

	public static InterfaceModelPaciente dao;
	
	EditText nome, dtnascimento;
	Button cadPaciente;
	RadioButton delPaciente,edPaciente;
	RadioGroup radioGroup;
	ImageButton back, logout;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        dao = new PacienteScript(this);
        setContentView(R.layout.paciente);
        
        back = (ImageButton) findViewById(R.bt.btBack);
		logout = (ImageButton) findViewById(R.bt.btLogoff);
        nome = (EditText) findViewById(R.paciente.nome);
        dtnascimento = (EditText) findViewById(R.paciente.dtnascimento);
        cadPaciente = (Button) findViewById(R.bt.cadPaciente);
       //cadPaciente.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        delPaciente = (RadioButton) findViewById(R.id.remover);
        edPaciente = (RadioButton) findViewById(R.id.editar);
        radioGroup.setVisibility(RadioGroup.GONE);
        delPaciente.setVisibility(RadioButton.GONE);
        edPaciente.setVisibility(RadioButton.GONE);
        
        /*Spinner spinner = (Spinner) findViewById(R.cbm.doenca);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.doencas_array,
        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/
        cadPaciente.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				save();
			}
		});
        
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//finish();
				startActivity(new Intent(getBaseContext(),MenuPaciente.class));
				
			}
		});
		
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), Login.class));
				
			}
		});
        
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
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// Fecha a tela
		finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Fecha o banco
		dao.fechar();
	}
}