package nutes.intelimed.model.user;


import android.database.SQLException;


/**
 * Interface de usuário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelUserDao{

	public abstract User search(String user,String password) throws UserOrPasswordIncorrectException, SQLException;

}