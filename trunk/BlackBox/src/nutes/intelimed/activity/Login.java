package nutes.intelimed.activity;


import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nutes.intelimed.R;
import nutes.intelimed.controller.IUser;
import nutes.intelimed.controller.UserController;
import nutes.intelimed.model.user.IModelUserDao;
import nutes.intelimed.model.user.User;
import nutes.intelimed.model.user.UserDao;
import nutes.intelimed.model.user.UserOrPasswordIncorrectException;
import nutes.intelimed.util.MD5Password;

/**
 * Classe responsável por apresentação da tela de autenticação de usuário
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Login extends Activity{
	
	//private IModelUserDao usersDao;
	private IUser users;
	private Button btLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       
        //usersDao = new UserDao(this);
        users = new UserController(this);
       
        btLogin = (Button) findViewById(R.bt.btLogin);
        btLogin.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				EditText etUser, etPassword;
				String strUser, strPassword;
				
				etUser = (EditText) findViewById(R.campo.user);
				etPassword = (EditText) findViewById(R.campo.password);
				
				strUser = etUser.getText().toString();
				strPassword = etPassword.getText().toString();
				
				try {
					users.login(strUser, MD5Password.getPassword(strPassword));
					//usersDao.login(user);
					init();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserOrPasswordIncorrectException e) {
					Toast.makeText(Login.this, "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
				}
			}
		}); 
    }
    
    /**
     * Método que direciona para Activity Menu
     * @return void
     */
    public void init(){
    	startActivity(new Intent(this, Menu.class));
    }
    
    /**
	 * 
	 * Implementação para botão voltar de Activity
	 * @param Indentificação de onclick
	 * @return value boolean
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	//usersDao.fechar();
	        finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//usersDao.fechar();
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}
}