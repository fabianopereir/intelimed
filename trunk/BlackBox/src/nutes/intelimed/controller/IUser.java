package nutes.intelimed.controller;

import android.database.SQLException;
import nutes.intelimed.model.user.User;
import nutes.intelimed.model.user.UserOrPasswordIncorrectException;

public interface IUser {
	
	/*public void insert(User user);
	public void delete(String user);
	public void update(User user);*/
	public void login(String user, String password) throws UserOrPasswordIncorrectException,SQLException;
}
