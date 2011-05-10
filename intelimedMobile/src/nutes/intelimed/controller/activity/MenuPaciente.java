package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPaciente extends Activity implements OnClickListener{
	Button btcadPaciente;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menupaciente);
		 
		btcadPaciente = (Button) findViewById(R.bt.opCadastro);
		btcadPaciente.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, CadastroPaciente.class));
	}

}

