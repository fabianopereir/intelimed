package nutes.intelimed.model.DAO;

import nutes.intelimed.model.IModelUser;
import nutes.intelimed.model.entity.User;
import nutes.intelimed.model.entity.User.Users;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
* Classe respons�vel por realizar consultas sobre usu�rios no banco de dados 
* @author Jamilson Batista (jamilsonbatista@gmail.com)
* @author Dyego Carlos (dyego12345@gmail.com)
*/
public class UserDao extends GenericDao implements IModelUser{

	// Nome da tabela
	public static final String NOME_TABELA = "usuario";
	
	public UserDao()
	{
		
	}
	public UserDao(Context ctx) {
		// Abre o banco de dados
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	/**
	 * Busca usu�rio e senha na base de dados
	 * @param User u (identificador de usu�rio)
	 * @return User 
	 */
	@Override
	public User login(User u) {
		User user = null;

		try {
			
			Cursor c = db.query(NOME_TABELA, User.colunas, Users.USUARIO + "='" + u.getVuser() + "'" + " AND " + Users.SENHA + "='" + u.getVpassword() + "'", null, null, null, null);
			
			if (c.getCount() > 0) {
				c.moveToFirst();
				user = new User();
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
       
	}
	
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
}