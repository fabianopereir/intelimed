package test;

import nutes.intelimed.model.DAO.UsuarioDao;
import nutes.intelimed.model.DAO.UsuarioScript;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class UsuarioDaoTest extends Activity{
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		//UsuarioDao repositorio = new UsuarioScript(this);
		UsuarioScript script;

        script = new UsuarioScript(this);

		
		//AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setMessage(repositorio.buscarFuncionario(2).toString());
		//builder.setMessage(repositorio.login(u));
        //builder.show();
		
	}

}
