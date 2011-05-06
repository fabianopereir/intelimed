package nutes.intelimed.controller.activity;

import nutes.intelimed.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{
	Button btCadastro;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.main);
	        setContentView(R.layout.menu);
	        btCadastro = (Button) findViewById(R.bt.menuCadastro);
			btCadastro.setOnClickListener(this);
	 	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		startActivity(new Intent(this, MenuCadastro.class));
		
	}

}
