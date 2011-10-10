package nutes.intelimed.communication;

/**
 * Classe responsável por fornecer constantes usadas para comunicação
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ServerConstants {
	
	//private final static String server = "lagarage.dyndns.biz/Intermediate/";
	private final static String server = "192.168.0.9:8080/Intermediate/";
	public final static String username = "admin";
	public final static String password = "123";

	public static String getContextFromPost() {
		return getContext(server); 
	}
	
	public static String getContextFromGet() {
		return getContext(server); 
	}
	
	private static String getContext(String ip) {
		return "http://" + ip + "arvore/rest/";
	}
	
}
