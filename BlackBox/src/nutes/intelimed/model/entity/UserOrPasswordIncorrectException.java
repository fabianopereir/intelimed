package nutes.intelimed.model.entity;

public class UserOrPasswordIncorrectException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserOrPasswordIncorrectException() {
		super("Usu�rio ou senha incorreto!");
	}
	
}
