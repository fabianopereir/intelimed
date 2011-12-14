package nutes.intelimed.communication;

/**
 * Classe responsável por fornecer constantes usadas para comunicação
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ServerConstants {
	
	//private final static String server = "192.168.0.9:8080/Intermediate/";
	private final static String server = "ec2-107-22-67-212.compute-1.amazonaws.com/intermediate";
	public final static String username = "admin";
	public final static String password = "123";

	public static String getContextFromPost() {
		return getContext(server+"/rest/evidencia"); 
	}
	
	public static String getContextFromGet() {
		return getContext(server+"/rest/arvore"); 
	}
	
	private static String getContext(String ip) {
		return "http://" + ip;
	}
	
}
