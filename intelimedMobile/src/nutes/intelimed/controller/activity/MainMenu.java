package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenu extends Activity{
	Button btMenuPaciente;
	ImageButton back, logout;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.main);
	        setContentView(R.layout.menu);
	        
	        back = (ImageButton) findViewById(R.bt.btBack);
			logout = (ImageButton) findViewById(R.bt.btLogoff);
			
	        btMenuPaciente = (Button) findViewById(R.bt.menuPaciente);
	        
	        back.setVisibility(ImageButton.GONE);
	        
	        btMenuPaciente.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startMenuPaciente();	
				}
			});
	        
	        logout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					startActivity(new Intent(getBaseContext(), Login.class));
					//	finish();
					
				}
			});
	        
	 	}

	
	public void startMenuPaciente() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, MenuPaciente.class));
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// Fecha a tela
		finish();
	}

}
