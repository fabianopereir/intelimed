package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{
	Button btMenuPaciente;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.main);
	        setContentView(R.layout.menu);
	        btMenuPaciente = (Button) findViewById(R.bt.menuPaciente);
	        btMenuPaciente.setOnClickListener(this);
	 	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, MenuPaciente.class));
		
	}

}
