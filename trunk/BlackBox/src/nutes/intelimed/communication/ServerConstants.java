package nutes.intelimed.communication;

public class ServerConstants {

	public static String getContextFromPost() {
		return getContext("10.0.2.2:8080"); 
	}
	
	public static String getContextFromGet() {
		return getContext("10.0.2.2:8080"); 
	}
	
	public static String getContextFromPut() {
		return getContext("10.0.2.2:8080"); 
	}
	
	private static String getContext(String ip) {
		return "http://" + ip + "/livro_android/arvore.txt";
	}
	
	
}
