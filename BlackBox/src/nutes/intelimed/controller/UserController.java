package nutes.intelimed.controller;

import android.content.Context;
import android.database.SQLException;
import nutes.intelimed.model.user.UserDao;
import nutes.intelimed.model.user.UserOrPasswordIncorrectException;

public class UserController implements IUser{
	private UserDao users;
	
	public UserController(Context ctx){
		users = new UserDao(ctx);
	}

	public void login(String user, String password) throws SQLException, UserOrPasswordIncorrectException {
		users.search(user, password);
	}

}
