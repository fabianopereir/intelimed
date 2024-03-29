package nutes.intelimed.communication;

import java.util.Map;

import org.json.JSONObject;


/**
 * Classe abstrata responsável por definir os métodos de Http
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public abstract class Http {

	public static Http getInstance() {
		return new HttpConnection();
	}

	public abstract String doGet(String url);
	public abstract Boolean doPost(String url, Map<String, JSONObject> params);
}