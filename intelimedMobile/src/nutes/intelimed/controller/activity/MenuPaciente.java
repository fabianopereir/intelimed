package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuPaciente extends Activity{
	Button btcadPaciente, btlistPaciente, btPesquisar;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menupaciente);
		
		btlistPaciente = (Button) findViewById(R.bt.opListar); 
		btcadPaciente = (Button) findViewById(R.bt.opCadastro);
		btPesquisar = (Button) findViewById(R.bt.opPesquisar);
		btcadPaciente.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				initCadPaciente();
			}
		});
		btlistPaciente.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list();
				///Toast.makeText(MenuPaciente.this, "pronto", Toast.LENGTH_SHORT).show();
			}
		});
		btPesquisar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				search();
			}
		});
	}
	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, CadastroPaciente.class));
	}*/
	public void initCadPaciente(){
		startActivity(new Intent(this,CadastroPaciente.class));
	}
	public void list()
	{
		Log.i("jamilson", "metodo list");
		startActivity(new Intent(this,ListarPaciente.class));
	}
	public void search(){
		startActivity(new Intent(this,SearchPaciente.class));
	}

}

