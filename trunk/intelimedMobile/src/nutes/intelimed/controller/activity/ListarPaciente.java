package nutes.intelimed.controller.activity;

import java.util.List;

import nutes.intelimed.controller.PacienteListAdapter;
import nutes.intelimed.model.InterfaceModelPaciente;
import nutes.intelimed.model.DAO.PacienteScript;
import nutes.intelimed.model.entity.Paciente;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

public class ListarPaciente extends ListActivity{
	public static InterfaceModelPaciente dao;
	private List<Paciente> pacientes;
	
	public void onCreate(Bundle iclicle){
		super.onCreate(iclicle);	
		dao = new PacienteScript(this);
		updateList();
	}
	protected void updateList() {
		Log.i("jamilson", "metodo updateList");
		// Pega a lista de pessoas e exibe na tela
		pacientes = dao.listarPacientes();
		Log.i("jamilson", "foi chamado o metodo listarPacientes");
		// Adaptador de lista customizado para cada linha de uma pessoa
		setListAdapter(new PacienteListAdapter(this, pacientes));
	}

}