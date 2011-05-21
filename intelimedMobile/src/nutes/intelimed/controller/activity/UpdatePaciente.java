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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class UpdatePaciente extends Activity implements OnClickListener{
	public static InterfaceModelPaciente dao;
	EditText nome, dtnascimento;
	Button upPaciente;
	Paciente paciente;
	RadioButton delPaciente,edPaciente;
	RadioGroup radioGroup;
	boolean del=false;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dao = new PacienteScript(this);
		setContentView(R.layout.paciente);
		
		nome = (EditText) findViewById(R.paciente.nome);
        dtnascimento = (EditText) findViewById(R.paciente.dtnascimento);
        upPaciente = (Button) findViewById(R.bt.cadPaciente);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        delPaciente = (RadioButton) findViewById(R.id.remover);
        edPaciente = (RadioButton) findViewById(R.id.editar);
        upPaciente.setText("Atualizar");
        upPaciente.setOnClickListener(this);
        radioGroup.setVisibility(RadioGroup.VISIBLE);
        delPaciente.setVisibility(RadioButton.VISIBLE);
        edPaciente.setVisibility(RadioButton.VISIBLE);
        //exibe os dados do paciente
		Intent intent = getIntent();
        if (intent!=null)
        {
        	paciente = (Paciente) intent.getSerializableExtra("paciente");
        	//String algumaString = intent.getString("nome");
        	nome.setText(paciente.nome);
        	dtnascimento.setText(paciente.datanascimento);
        }
        //Desabilita campos. Só para exibção.
        nome.setEnabled(false);
        dtnascimento.setEnabled(false);
        
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				boolean edit = R.id.editar == checkedId;
				boolean delete = R.id.remover == checkedId;
				if (edit) {
					nome.setEnabled(true);
		            dtnascimento.setEnabled(true);
					Log.i("jamilson", "Marcou radio Sim: " + checkedId);
				} else if (delete) {
					del = true;
					nome.setEnabled(false);
		            dtnascimento.setEnabled(false);
					Log.i("jamilson", "Marcou radio Não: " + checkedId +""+ del);
				}
			}
		});
		
	}
	
	@Override
	public void onClick(View v) {
		if(del){
			deletar();
		}else{
			update();
		}
	}
	
	public void update(){
		paciente.nome = nome.getText().toString();
		paciente.datanascimento = dtnascimento.getText().toString();
		dao.atualizar(paciente);
		setResult(RESULT_OK, new Intent());
		// Fecha a tela
		finish();
	}
	
	public void deletar(){
		paciente.nome = nome.getText().toString();
		paciente.datanascimento = dtnascimento.getText().toString();
		dao.deletar(paciente.id);
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
