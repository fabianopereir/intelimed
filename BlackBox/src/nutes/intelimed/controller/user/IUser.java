package nutes.intelimed.controller.user;

import android.database.SQLException;
import nutes.intelimed.model.user.UserOrPasswordIncorrectException;

public interface IUser {
	
	public void login(String user, String password) throws UserOrPasswordIncorrectException,SQLException;
	
}
