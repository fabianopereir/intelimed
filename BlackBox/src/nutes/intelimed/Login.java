package nutes.intelimed;


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
import nutes.intelimed.communication.TreeUpdate;
import nutes.intelimed.controller.activity.Menu;
import nutes.intelimed.model.IModelUser;
import nutes.intelimed.model.MD5Password;
import nutes.intelimed.model.UserScript;
import nutes.intelimed.model.entity.User;

/**
 * Classe responsável por apresentação da tela de autenticação de usuário
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Login extends Activity implements OnClickListener{
	
	public static IModelUser dao;
	
	Button btlogin;
	EditText user, password;
	
	User usuario = new User();

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       
        dao = new UserScript(this);
       
        btlogin = (Button) findViewById(R.bt.btLogin);
        btlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String Vuser, Vpassword;
				User userFinal=null;
				
				user = (EditText) findViewById(R.campo.user);
				password = (EditText) findViewById(R.campo.password);
				
				Vuser = user.getText().toString();
				Vpassword = password.getText().toString();
				try {
					usuario.setVuser(Vuser);
					usuario.setVpassword(MD5Password.getPassword(Vpassword));
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userFinal = dao.login(usuario);
				if (userFinal!=null)
				{
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

    /**
     * Método que direciona para Activity Menu
     * @return void
     */
    public void init(){
    	startActivity(new Intent(this, Menu.class));
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