package nutes.intelimed.communication.helper;

import java.util.Map;

/**
 * Classe abstrata respons�vel por definir os m�todos de Http
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public abstract class Http {

	public static Http getInstance() {
		return new HttpNormalImpl();
	}

	public abstract String doGet(String url);
	public abstract Boolean doPost(String url, Map map);
}