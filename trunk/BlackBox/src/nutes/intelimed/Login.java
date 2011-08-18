package nutes.intelimed;

import nutes.intelimed.communication.helper.Http;
import nutes.intelimed.controller.activity.FormDiagnostic;
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


public class Login extends Activity implements OnClickListener,Runnable {

	protected static final String CATEGORIA = "nutes";
	protected static final String URL = "http://10.0.2.2:8080/livro_android/arvore.txt";
	//Handler utilizado para atualizar a View
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
	public void onClick(View view) {
		dialog = ProgressDialog.show(this,"InteliMED", "Efetuando login...", false,true);

		//faz download em uma Thread
		new Thread(this).start();
	}
	public void run() {
		try {
			// faz o download
			final String arquivo = Http.getInstance(Http.NORMAL).downloadArquivo(URL);

			Log.i(CATEGORIA,"Texto retornado: " + arquivo);

			//Precisa do Handler para atualizar a view de outra thread
			handler.post(new Runnable() {
				public void run() {
					TextView text = (TextView) findViewById(R.id.texto);
					text.setText(arquivo);
					parserJason = new ParserSimulation();
					parserJason.parserJson(arquivo);
					
					Intent it = new Intent(getBaseContext(), FormDiagnostic.class);
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
