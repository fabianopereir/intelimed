package nutes.intelimed.model.user;

public class UserOrPasswordIncorrectException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserOrPasswordIncorrectException() {
		super("Usuário ou senha incorreto!");
	}
	
}
