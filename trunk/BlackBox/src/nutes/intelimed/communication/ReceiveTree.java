package nutes.intelimed.communication;

import nutes.intelimed.ParserSimulation;
import nutes.intelimed.communication.helper.Http;
import nutes.intelimed.service.BlackBox;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * Classe responsável por realizar download da árvore do servidor
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ReceiveTree extends Thread implements Runnable {

	protected static final String CATEGORIA = "nutes";
	
	private Handler handler = new Handler();
	private ParserSimulation parserJason;
	private BlackBox bb;

	public ReceiveTree(BlackBox bb) {
		this.bb = bb;
	}
	
	/**
	 * Faz download de um arquivo texto e atualiza a tela
	 * @return void
	 */
	public void run() {
		try {
			
			//final String arquivo = Http.getInstance().doGet(ServerConstants.getContextFromGet());

			//Log.i(CATEGORIA,"Texto retornado: " + arquivo);

			handler.post(new Runnable() {
				public void run() {
					try {
						parserJason = new ParserSimulation(bb);
						parserJason.parserJson("teste");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(),e);
		}
	}
}
