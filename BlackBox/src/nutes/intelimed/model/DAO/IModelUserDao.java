package nutes.intelimed.model.DAO;


import android.database.SQLException;
import nutes.intelimed.model.entity.User;
import nutes.intelimed.model.entity.UserOrPasswordIncorrectException;


/**
 * Interface de usuário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelUserDao{
	public abstract void login(User user) throws UserOrPasswordIncorrectException, SQLException;
	public abstract void fechar();
}