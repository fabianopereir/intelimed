package nutes.intelimed.model.entity;

/**
 * Entidade Usuário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class User {
	
	private String user;
	private String password;
	
	public User(){
	}
	
	public User(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Classe interna com atributos da tabela usuários
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 */
	public static final class UsersTableConstants  {
		public static final String USUARIO = "user";
		public static final String SENHA = "password";
		public static final String ID = "id";
		
		public static String[] colunas = new String[] { UsersTableConstants.ID, UsersTableConstants.USUARIO, UsersTableConstants.SENHA };
		
		private UsersTableConstants() {
		
		}
	
	}
}