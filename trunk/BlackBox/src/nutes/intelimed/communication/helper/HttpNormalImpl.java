package nutes.intelimed.communication.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import android.util.Log;

/**
 * Classe que encapsula as requests HTTP utilizando a "HttpURLConnection"
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class HttpNormalImpl extends Http {
	private final String CATEGORIA = "nutes";

	/**
	 * Método responsável por realizar download de arquivo texto do servidor
	 * @param String url
	 * @return String
	 */
	@Override
	public final String doGet(String url) {
		Log.i(CATEGORIA, "Http.downloadArquivo: " + url);
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.connect();

			InputStream in = conn.getInputStream();

			String arquivo = readString(in);

			conn.disconnect();

			return arquivo;
		} catch (MalformedURLException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Realiza o envio ao servidor
	 * @param String url
	 * @param Map params
	 * @return String url
	 */
	@Override
	public Boolean doPost(String url, Map params) {
		try {
			String queryString = getQueryString(params);
			Boolean texto = doPost(url, queryString);
			Log.i(CATEGORIA, "Valor de texto: "+texto);
			return texto;
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
		return false;
	}

	/**
	 * Faz um requsição POST na URL informada e retorna o texto
	 * Os parâmetros são enviados ao servidor
	 * @param String url
	 * @param String params
	 * @return	
	 * @throws IOException
	 */
	private Boolean doPost(String url, String params) throws IOException {
		Log.i(CATEGORIA, "Http.doPost: " + url + "?" + params);
		URL u = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		conn.connect();

		OutputStream out = conn.getOutputStream();
		byte[] bytes = params.getBytes("UTF8");
		out.write(bytes);
		out.flush();
		out.close();

		InputStream in = conn.getInputStream();

		String texto = readString(in);
		Log.i(CATEGORIA, "Valor de texto após read: "+texto);
		
		conn.disconnect();

		return Boolean.valueOf(texto);
	}

	// 
	/**
	 * Transforma o HashMap em uma query string fazer o POST
	 * @param Map params
	 * @return String urlParams
	 * @throws IOException
	 */
	private String getQueryString(Map params) throws IOException {
		if (params == null || params.size() == 0) {
			return null;
		}
		String urlParams = null;
		Iterator e = (Iterator) params.keySet().iterator();
		while (e.hasNext()) {
			String chave = (String) e.next();
			Object objValor = params.get(chave);
			String valor = objValor.toString();
			urlParams = urlParams == null ? "" : urlParams + "&";
			urlParams += chave + "=" + valor;
		}
		return urlParams;
	}

	
	/**
	 * Faz a leitura do array de bytes da InputStream retornada
	 * @param InputStream in
	 * @return byte[] - array de bytes consumido
	 * @throws IOException
	 */
	private byte[] readBytes(InputStream in) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
			byte[] bytes = bos.toByteArray();
			return bytes;
		} finally {
			bos.close();
			in.close();
		}
	}

	/**
	 * Faz a leitura do texto da InputStream retornada
	 * @param InputStream in
	 * @return String texto - texto consumido
	 * @throws IOException
	 */
	private String readString(InputStream in) throws IOException {
		byte[] bytes = readBytes(in);
		String texto = new String(bytes);
		Log.i(CATEGORIA, "Http.readString: " + texto);
		return texto;
	}
}