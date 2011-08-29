package nutes.intelimed.communication;

import nutes.intelimed.ParserSimulation;
import nutes.intelimed.R;
import nutes.intelimed.communication.helper.Http;
import nutes.intelimed.controller.activity.Menu;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * Classe responsável por realizar download da árvore do servidor
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class TreeUpdate extends Activity implements OnClickListener, Runnable {

	protected static final String CATEGORIA = "nutes";
	//protected static final String URL = "http://10.0.2.2:8080/livro_android/arvore.txt";
	protected static final String URL = "http://lagarage.dyndns.biz:35355/intermediate/rest/arvore/";
	private Handler handler = new Handler();
	private ProgressDialog dialog;
	
	private ParserSimulation parserJason;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.arquivo);

		Button b = (Button) findViewById(R.id.btDownload);
		b.setOnClickListener(this);
	}
	
	/**
	 * 
	 * Exibe uma barra de progresso para realizar login
	 * @param view - contexto
	 * @return void
	 */
	public void onClick(View view) {
		dialog = ProgressDialog.show(this,"InteliMED", "Efetuando login...", false,true);

		new Thread(this).start();
	}
	
	/**
	 * Faz download de um arquivo texto e atualiza a tela
	 * @return void
	 */
	public void run() {
		try {
			final String arquivo = Http.getInstance(Http.NORMAL).downloadArquivo(URL);

			Log.i(CATEGORIA,"Texto retornado: " + arquivo);

			handler.post(new Runnable() {
				public void run() {
					TextView text = (TextView) findViewById(R.id.texto);
					text.setText(arquivo);
					parserJason = new ParserSimulation();
					parserJason.parserJson(arquivo);
					
					Intent it = new Intent(getBaseContext(), Menu.class);
					startActivity(it);
				}
			});
		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(),e);
		} finally {
			dialog.dismiss();
		}
	}
	}
