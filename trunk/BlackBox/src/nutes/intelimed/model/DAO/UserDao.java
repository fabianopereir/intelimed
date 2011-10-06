package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.User;
import nutes.intelimed.model.entity.User.UsersTableConstants;
import nutes.intelimed.model.entity.UserOrPasswordIncorrectException;
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
	
	/**
	 * Busca usuário e senha na base de dados
	 * @param User user (identificador de usuário)
	 */
	public void login(User user) throws UserOrPasswordIncorrectException,SQLException {
		Cursor c = db.query(NOME_TABELA, UsersTableConstants.colunas, UsersTableConstants.USUARIO + "='" + user.getUser() + "'" + " AND " + UsersTableConstants.SENHA + "='" + user.getPassword() + "'", null, null, null, null);
			
		if (c.getCount() > 0) {
			c.close();
		}else{
			c.close();
			throw new UserOrPasswordIncorrectException();
		}	
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}
}