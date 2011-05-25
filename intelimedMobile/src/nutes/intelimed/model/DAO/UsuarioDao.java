package nutes.intelimed.model.DAO;

import nutes.intelimed.model.InterfaceModelUsuario;
import nutes.intelimed.model.entity.Usuario;
import nutes.intelimed.model.entity.Usuario.Usuarios;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDao implements InterfaceModelUsuario{

	// Nome do banco
	private static final String NOME_BANCO = "inteliMobile";
	// Nome da tabela
	public static final String NOME_TABELA = "usuario";

	protected SQLiteDatabase db;
	
	public UsuarioDao()
	{
		
	}
	public UsuarioDao(Context ctx) {
		// Abre o banco de dados
		
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	/* public Usuario login(Usuario u){
		 
		 Usuario user = null;

			try {
				
				// Idem a: SELECT _id,nome,cpf from usuario where nome = ?
				//Cursor c = db.query(NOME_TABELA, Usuario.colunas, Usuarios.USUARIO + "='" + u.getVuser() + "'", null, null, null, null);
				Cursor c = db.query(NOME_TABELA, Usuario.colunas, Usuarios.USUARIO + "='" + u.getVuser() + "'" + " AND " + Usuarios.SENHA + "='" + u.getVpassword() + "'", null, null, null, null);
				
				//Log.i("jamilson", "FIMPass");	
				// Se encontrou...
				//if (c.moveToNext()) {
				if (c.getCount() > 0) {
					c.moveToFirst();
					user = new Usuario();
					// utiliza os métodos getLong(), getString(), getInt(), etc para recuperar os valores
					//user.id = c.getLong(0);
					Log.i("jamilson", "Result1: "+c.getString(0));
					Log.i("jamilson", "Result2: "+c.getString(1));
					user.Vuser = c.getString(1);
					user.Vpassword = c.getString(2);
					return u;
				}else 
				{
					return null;
				}
				
			} catch (SQLException e) {
				Log.e("jamilson", "Erro ao buscar a usuario: " + e.toString());
				e.printStackTrace();
				return null;
			}	 	
	        //return u;
	    }
	 */
	@Override
	public Usuario login(Usuario u) {
		Usuario user = null;

		try {
			
			// Idem a: SELECT _id,nome,cpf from usuario where nome = ?
			//Cursor c = db.query(NOME_TABELA, Usuario.colunas, Usuarios.USUARIO + "='" + u.getVuser() + "'", null, null, null, null);
			Cursor c = db.query(NOME_TABELA, Usuario.colunas, Usuarios.USUARIO + "='" + u.getVuser() + "'" + " AND " + Usuarios.SENHA + "='" + u.getVpassword() + "'", null, null, null, null);
			
			//Log.i("jamilson", "FIMPass");	
			// Se encontrou...
			//if (c.moveToNext()) {
			if (c.getCount() > 0) {
				c.moveToFirst();
				user = new Usuario();
				// utiliza os métodos getLong(), getString(), getInt(), etc para recuperar os valores
				//user.id = c.getLong(0);
				Log.i("jamilson", "Result1: "+c.getString(0));
				Log.i("jamilson", "Result2: "+c.getString(1));
				user.Vuser = c.getString(1);
				user.Vpassword = c.getString(2);
				return u;
			}else 
			{
				return null;
			}
			
		} catch (SQLException e) {
			Log.e("jamilson", "Erro ao buscar a usuario: " + e.toString());
			e.printStackTrace();
			return null;
		}	 	
        //return u;
	}
	/**
	 * Fecha o banco
	 */
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
}