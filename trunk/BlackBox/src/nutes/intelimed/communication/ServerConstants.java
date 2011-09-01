package nutes.intelimed.communication;

/**
 * Classe responsável por fornecer constantes usadas para comunicação
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ServerConstants {

	public static String getContextFromPost() {

		return getContext("192.168.0.9:8080"); 
		//192.168.0.9

	}
	
	public static String getContextFromGet() {

		return getContext("192.168.0.9:8080"); 

	}
	
	public static String getContextFromPut() {
		return getContext("192.168.0.9:8080"); 
	}
	
	private static String getContext(String ip) {

	//return "http://" + ip + "/livro_android/arvore.txt";
	return "http://" + ip + "/livro_android/calculadoraServlet";

	}
	
	
}
