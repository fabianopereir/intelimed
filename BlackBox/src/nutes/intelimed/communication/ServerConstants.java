package nutes.intelimed.communication;

public class ServerConstants {

	public static String getContextFromSendPost() {
		return getContext("10.0.2.2:8080"); 
	}
	
	public static String getContextFromSendGet() {
		return getContext("10.0.2.2:8080"); 
	}
	
	public static String getContextFromSendPut() {
		return getContext("10.0.2.2:8080"); 
	}
	
	private static String getContext(String ip) {
		return "http://" + ip + "/server";
	}
	
	
}
