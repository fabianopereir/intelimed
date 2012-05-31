package nutes.intelimed.communication;

/**
 * Classe responsável por fornecer constantes usadas para comunicação
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ServerConstants {
	
	private final static String server = "10.0.0.10:9090/Intermediate";
	//private final static String server = "ec2-23-20-107-254.compute-1.amazonaws.com/intermediate";
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
