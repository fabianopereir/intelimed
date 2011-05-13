package nutes.intelimed.controller;

import java.util.List;

import nutes.intelimed.R;
import nutes.intelimed.model.entity.Paciente;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PacienteListAdapter extends BaseAdapter{

	private Context context;
	private List<Paciente> lista;

	public PacienteListAdapter(Context context, List<Paciente> lista) {
		Log.i("jamilson", "metodo listarPacientes");
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		Paciente c = lista.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.paciente_row_table, null);

		// Atualiza o valor do TextView
		TextView nome = (TextView) view.findViewById(R.table.nome);
	
		Log.i("jamilson", "valor de c.nome "+c.nome);
		nome.setText(c.nome);

		TextView datNascimento = (TextView) view.findViewById(R.table.datNascimento);
		datNascimento.setText(c.datanascimento);

		return view;
	}
}
