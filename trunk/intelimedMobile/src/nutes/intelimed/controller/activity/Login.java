package nutes.intelimed.controller.activity;


import java.security.NoSuchAlgorithmException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import nutes.intelimed.R;
import nutes.intelimed.model.MD5Password;
import nutes.intelimed.model.DAO.UsuarioDao;
import nutes.intelimed.model.DAO.UsuarioScript;
import nutes.intelimed.model.entity.Usuario;

public class Login extends Activity implements OnClickListener{
	Button btlogin;
	EditText user, password;
	UsuarioDao dao;
	
	Usuario usuario = new Usuario();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        new UsuarioScript(this);
       
        btlogin = (Button) findViewById(R.bt.btLogin);
        btlogin.setOnClickListener(this); 
    }
    
    @Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// Fecha a tela
		finish();
	}

	public void onClick(View view) {
		String Vuser, Vpassword;
		Usuario userFinal=null;
		
		user = (EditText) findViewById(R.campo.user);
		password = (EditText) findViewById(R.campo.password);
		
		Vuser = user.getText().toString();
		Vpassword = password.getText().toString();
		try {
			//Toast.makeText(Login.this, Vuser + " " + MD5Password.getPassword(Vpassword), Toast.LENGTH_SHORT).show();
			usuario.setVuser(Vuser);
			usuario.setVpassword(MD5Password.getPassword(Vpassword));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new UsuarioDao(this);
		userFinal = dao.login(usuario);
		if (userFinal!=null)
		{
			startActivity(new Intent(this, MainMenu.class));
		}else{
			Toast.makeText(Login.this, "Usu�rio ou senha incorreto!", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Fecha o banco
		dao.fechar();
	}
}