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
import nutes.intelimed.model.InterfaceModelUsuario;
import nutes.intelimed.model.MD5Password;
import nutes.intelimed.model.DAO.UsuarioDao;
import nutes.intelimed.model.DAO.UsuarioScript;
import nutes.intelimed.model.entity.Usuario;

public class Login extends Activity implements OnClickListener{
	
	public static InterfaceModelUsuario dao;
	
	Button btlogin;
	EditText user, password;
	//UsuarioDao dao;
	
	Usuario usuario = new Usuario();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       
        dao = new UsuarioScript(this);
       
        btlogin = (Button) findViewById(R.bt.btLogin);
        btlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
				//userFinal = dao.login(usuario);
				userFinal = dao.login(usuario);
				if (userFinal!=null)
				{
					//startActivity(new Intent(this, MenuPaciente.class));
					init();
				}else{
					Toast.makeText(Login.this, "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
				}
				
			}
		}); 
    }
    
    @Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// Fecha a tela
		finish();
	}

    public void init(){
    	startActivity(new Intent(this, MainMenu.class));
    }
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Fecha o banco
		dao.fechar();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}