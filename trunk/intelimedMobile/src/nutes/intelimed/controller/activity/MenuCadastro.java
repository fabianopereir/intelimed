package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import nutes.intelimed.model.DAO.PacienteDao;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MenuCadastro extends Activity implements OnClickListener{
	
	EditText nome, dtnascimento;
	Button cadPaciente;
	PacienteDao dao;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        new PacienteScript(this);
        setContentView(R.layout.paciente);
        //setContentView(ScrollView.FOCUS_UP);
        nome = (EditText) findViewById(R.paciente.nome);
        dtnascimento = (EditText) findViewById(R.paciente.dtnascimento);
        cadPaciente = (Button) findViewById(R.bt.cadPaciente);
        cadPaciente.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		save();
	}
	public void save()
	{
		Paciente paciente = new Paciente();
		
		paciente.nome = nome.getText().toString();
		paciente.datanascimento = dtnascimento.getText().toString();

		// Salvar
		//salvarPessoa(paciente);
		dao = new PacienteDao(this);
		dao.save(paciente);
		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}
	

}
