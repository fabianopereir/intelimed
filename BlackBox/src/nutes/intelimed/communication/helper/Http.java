package nutes.intelimed.communication.helper;

import java.util.Map;

/**
 * Classe abstrata responsável por definir os métodos de Http e uma 
 * factory para criar a implementação correta
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 *
 */
public abstract class Http {
	public static final int NORMAL 	= 1;
	public static final int JAKARTA = 2;
	public static Http getInstance(int tipo){
		switch (tipo) {
			case NORMAL:
				return new HttpNormalImpl();
		default:
			return new HttpNormalImpl();
		}
	}
	public abstract String downloadArquivo(String url);
	public abstract byte[] downloadImagem(String url);
	public abstract Boolean doPost(String url, Map map);
}