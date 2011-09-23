package nutes.intelimed.communication.helper;

/**
 * Classe responsável por fornecer constantes usadas para comunicação
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ServerConstants {
	
	//private final static String server = "lagarage.dyndns.biz/Intermediate/";
	private final static String server = "localhost:8080/Intermediate/";
	private final static String username = "admin";
	private final static String password = "123";

	public static String getContextFromPost() {
		return getContext(server); 
	}
	
	public static String getContextFromGet() {
		return getContext(server); 
	}
	
	private static String getContext(String ip) {
		return "http://" + ip + "arvore/rest/";
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}
	
}
