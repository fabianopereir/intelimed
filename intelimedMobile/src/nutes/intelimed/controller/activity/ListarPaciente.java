package nutes.intelimed.controller.activity;

import java.util.List;

import test.MyList;



import nutes.intelimed.R;
import nutes.intelimed.controller.PacienteListAdapter;
import nutes.intelimed.model.InterfaceModelPaciente;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;
import nutes.intelimed.model.entity.Paciente.Pacientes;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListarPaciente extends ListActivity{
	public static InterfaceModelPaciente dao;
	private List<Paciente> pacientes;
	protected static final int INSERIR_EDITAR = 1;
	
	public void onCreate(Bundle iclicle){
		super.onCreate(iclicle);	
		dao = new PacienteScript(this);
		//setContentView(R.layout.main);
		updateList();
	}
	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada pendente na tela
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}
	protected void updateList() {
		Log.i("jamilson", "metodo updateList");
		pacientes = dao.listarPacientes();
		Log.i("jamilson", "foi chamado o metodo listarPacientes");
		setListAdapter(new PacienteListAdapter(this, pacientes));
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int posicao, long id) {
		Log.i("jamilson","chamado onListItemClick - clicou em um item da lista");
		super.onListItemClick(l, v, posicao, id);
		editarPaciente(posicao);
	}

	// Recupera o id da pessoa, e abre a tela de edição
	protected void editarPaciente(int posicao) {
		Log.i("jamilson", "entrou em editarPaciente");
		// Usuário clicou em alguma pessoa da lista
		// Recupera a pessoa selecionado
		Paciente paciente = pacientes.get(posicao);
		// Cria a intent para abrir a tela de editar
		if(paciente!=null){
			Intent intent = new Intent();
			intent.setClassName(this, "nutes.intelimed.controller.activity.UpdatePaciente");
			intent.putExtra("paciente", paciente);
			startActivity(intent);
		}else
		{
			Toast.makeText(ListarPaciente.this, "Erro ao exibir Paciente", Toast.LENGTH_SHORT).show();
		}
		//Intent it = new Intent(this, UpdatePaciente.class);
		// Passa o id da pessoa como parâmetro
		//it.putExtra(Pacientes._ID, paciente.id);
		// Abre a tela de edição
		//startActivity(it);
		Log.i("jamilson", "iniciou activity");
	}
}