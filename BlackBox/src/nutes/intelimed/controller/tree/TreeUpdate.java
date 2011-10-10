package nutes.intelimed.controller.tree;

import nutes.intelimed.communication.Http;
import nutes.intelimed.communication.ServerConstants;
import nutes.intelimed.util.Parser;
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
public class TreeUpdate extends Thread implements Runnable {

	protected static final String CATEGORIA = "nutes";
	private Handler handler = new Handler();
	private Parser parserJason;
	private ITree tree;

	public TreeUpdate(Context ctx) {
		this.tree = new TreeController(ctx);
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
						update(arquivo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(),e);
		}
		
	}
	
	private void update(String arquivo) throws Exception{
		parserJason = new Parser(tree);
		parserJason.parserJson(arquivo);
	}
}
