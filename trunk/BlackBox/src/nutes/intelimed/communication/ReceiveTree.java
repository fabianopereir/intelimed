package nutes.intelimed.communication;

import nutes.intelimed.communication.helper.Http;
import nutes.intelimed.communication.helper.ServerConstants;
import nutes.intelimed.controller.util.Parser;
import nutes.intelimed.controller.util.TreeUpdate;
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
	private Parser parserJason;
	private TreeUpdate tree;

	public ReceiveTree(TreeUpdate tree) {
		this.tree = tree;
	}
	
	/**
	 * Faz download de um arquivo texto e atualiza a tela
	 * @return void
	 */
	public void run() {
		try {
			
			final String arquivo = Http.getInstance().doGet(ServerConstants.getContextFromGet());

			Log.i(CATEGORIA,"Texto retornado: " + arquivo);

			handler.post(new Runnable() {
				public void run() {
					try {
						parserJason = new Parser(tree);
						parserJason.parserJson(arquivo);
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
