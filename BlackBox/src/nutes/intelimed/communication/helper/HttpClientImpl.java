package nutes.intelimed.communication.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.util.Log;

/**
 * Classe que encapsula as requests HTTP utilizando a "HttpClient"
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class HttpClientImpl extends Http {
	private final String CATEGORIA = "nutes";

	/**
	 * Método responsável por realizar download de arquivo texto do servidor
	 * @param String url
	 * @return String
	 */
	@Override
	public final String downloadArquivo(String url) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);

			Log.i(CATEGORIA, "request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);

			Log.i(CATEGORIA, "----------------------------------------");
			Log.i(CATEGORIA, String.valueOf(response.getStatusLine()));
			Log.i(CATEGORIA, "----------------------------------------");

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				Log.i(CATEGORIA, "Lendo resposta");
				InputStream in = entity.getContent();
				String texto = readString(in);
				return texto;
			}
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Método responsável por realizar download de imagem do servidor
	 * @param String url
	 * @return byte[] - array de bytes da imagem
	 */
	@Override
	public final byte[] downloadImagem(String url) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);

			Log.i(CATEGORIA, "request " + httpget.getURI());

			HttpResponse response = httpclient.execute(httpget);

			Log.i(CATEGORIA, "----------------------------------------");
			Log.i(CATEGORIA, String.valueOf(response.getStatusLine()));
			Log.i(CATEGORIA, "----------------------------------------");

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				Log.i(CATEGORIA, "Lendo resposta...");
				InputStream in = entity.getContent();
				byte[] bytes = readBytes(in);
				Log.i(CATEGORIA, "Resposta: " + bytes);
				return bytes;
			}
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Faz um requsição POST na URL informada e retorna o texto
	 * Os parâmetros são enviados ao servidor
	 * @param String url
	 * @param String params
	 * @return
	 * @throws IOException
	 */
	@Override
	public final String doPost(String url, Map map) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			Log.i(CATEGORIA, "HttpClient.post " + httpPost.getURI());

			List<NameValuePair> params = getParams(map);
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			Log.i(CATEGORIA, "HttpClient.params " + params);

			HttpResponse response = httpclient.execute(httpPost);

			Log.i(CATEGORIA, "----------------------------------------");
			Log.i(CATEGORIA, String.valueOf(response.getStatusLine()));
			Log.i(CATEGORIA, "----------------------------------------");

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream in = entity.getContent();
				String texto = readString(in);
				Log.i(CATEGORIA, "Resposta: " + texto);
				return texto;
			}
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
		return null;
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
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				bos.write(buf, 0, len);
			}

			byte[] bytes = bos.toByteArray();
			return bytes;
		} finally {
			bos.close();
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
		return texto;
	}

	/**
	 * Faz a leitura do texto da InputStream retornada
	 * @param Map map
	 * @return List<NameValuePair>
	 * @throws IOException
	 */
	private List<NameValuePair> getParams(Map map) throws IOException {
		if (map == null || map.size() == 0) {
			return null;
		}

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		Iterator e = (Iterator) map.keySet().iterator();
		while (e.hasNext()) {
			String name = (String) e.next();
			Object value = map.get(name);
			params.add(new BasicNameValuePair(name, String.valueOf(value)));
		}

		return params;
	}
}
