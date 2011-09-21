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

		return getContext("lagarage.dyndns.biz/Intermediate/"); 
		//192.168.0.9

	}
	
	public static String getContextFromGet() {

		return getContext("lagarage.dyndns.biz/Intermediate/"); 

	}
	
	public static String getContextFromPut() {
		return getContext("lagarage.dyndns.biz/Intermediate/"); 
	}
	
	private static String getContext(String ip) {

	//return "http://" + ip + "/livro_android/arvore.txt";
	return "http://" + ip + "arvore/rest";

	}
	
	
}
