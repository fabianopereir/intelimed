package nutes.intelimed.test.user;

import nutes.intelimed.activity.Login;
import nutes.intelimed.model.user.User;
import nutes.intelimed.model.user.UserDao;
import nutes.intelimed.model.user.UserOrPasswordIncorrectException;
import nutes.intelimed.util.MD5Password;
import android.database.SQLException;
import android.test.ActivityInstrumentationTestCase2;

public class UserDaoTest extends ActivityInstrumentationTestCase2<Login> {
	
	private UserDao users;
	private String correctPassword;
	private String incorrectPassword;
	private String correctUser;
	private String incorrectUser;

	public UserDaoTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		users = new UserDao(getActivity().getBaseContext()); 
		this.correctPassword = MD5Password.getPassword("123");
		this.incorrectPassword = MD5Password.getPassword("*1234");
		this.correctUser = "nutes";
		this.incorrectUser = "jurema";
	}
	
	public void testSearch(){	
		try {
			assertEquals((new User(this.correctUser,this.correctPassword)).toString(), users.search(this.correctUser,this.correctPassword).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UserOrPasswordIncorrectException e) {
			fail();
		} 
	}
	
	public void testIncorrectPasswordException(){
		try {
			assertEquals((new User(this.correctUser,this.incorrectPassword)).toString(), users.search(this.correctUser,this.incorrectPassword).toString());
			fail();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UserOrPasswordIncorrectException e) {
			
		} 
	}
	
	public void testIncorrectUserException(){
		try {
			assertEquals((new User(this.incorrectUser,this.correctPassword)).toString(), users.search(this.incorrectUser,this.incorrectPassword).toString());
			fail();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UserOrPasswordIncorrectException e) {
			
		} 
	}
}
