package nutes.intelimed.communication;

/**
 * Classe responsável por fornecer constantes usadas para comunicação
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ServerConstants {
	
	private final static String server = "lagarage.dyndns.biz/Intermediate/";
	//private final static String server = "localhost:8080/Intermediate-0.1/";

	public static String getContextFromPost() {
		return getContext(server); 
	}
	
	public static String getContextFromGet() {
		return getContext(server); 
	}
	
	public static String getContextFromPut() {
		return getContext(server); 
	}
	
	private static String getContext(String ip) {

	//return "http://" + ip + "/livro_android/arvore.txt";
	return "http://" + ip + "arvore/rest";

	}
	
	
}
