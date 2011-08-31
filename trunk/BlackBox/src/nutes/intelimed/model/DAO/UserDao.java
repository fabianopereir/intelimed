package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.User;
import nutes.intelimed.model.entity.User.Users;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

/**
* Classe responsável por realizar consultas sobre usuários no banco de dados 
* @author Jamilson Batista (jamilsonbatista@gmail.com)
* @author Dyego Carlos (dyego12345@gmail.com)
*/
public class UserDao extends GenericDao implements IModelUserDao{

	public static final String NOME_TABELA = "usuario";
	
	public UserDao(){
		
	}
	
	public UserDao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	/**
	 * Busca usuário e senha na base de dados
	 * @param User u (identificador de usuário)
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
				user.Vuser = c.getString(1);
				user.Vpassword = c.getString(2);
				return u;
			}else 
			{
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	 	
       
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}
}