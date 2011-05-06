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
	//UsuarioDao dao = new UsuarioDao();
	UsuarioDao dao;
	
	Usuario usuario = new Usuario();
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.login);
        
        new UsuarioScript(this);
       
        btlogin = (Button) findViewById(R.bt.btLogin);
        btlogin.setOnClickListener(this); 
        //new UsuarioScript(this);
    }
    
    @Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada pendente na tela
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
			//Log.i("jamilson", "Senha: "+MD5Password.getPassword(Vpassword));
			usuario.setVuser(Vuser);
			usuario.setVpassword(MD5Password.getPassword(Vpassword));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Toast.makeText(Login.this, usuario.getVuser() + " " + usuario.getVpassword(), Toast.LENGTH_SHORT).show();
		//userFinal = dao.login(usuario);
		dao = new UsuarioDao(this);
		userFinal = dao.login(usuario);
		//userFinal = validaLogin(Vuser);

		
		if (userFinal!=null)
		{
			startActivity(new Intent(this, MainMenu.class));
			
			//startActivityForResult(new Intent(this, MainMenu.class), "1", 1);
			//Toast.makeText(Login.this, "Sucesso!!!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(Login.this, "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
			//Log.i("jamilson", "Senha: "+);
		}
	}
	// Busca uma pessoa pelo nome
	/*protected Usuario validaLogin(String vvuser) {
		Usuario usuarioo = dao.login(vvuser);
		return usuarioo;
	}*/
}
