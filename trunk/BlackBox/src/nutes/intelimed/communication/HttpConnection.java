package nutes.intelimed.communication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;


/**
 * Classe que encapsula as requests HTTP utilizando a "HttpURLConnection"
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class HttpConnection extends Http {
	private final String CATEGORIA = "nutes";
	
	/**
	 * Método responsável por realizar download de arquivo texto do servidor
	 * @param String url
	 * @return String
	 */
	@Override
	public final String doGet(String url) {
		Log.i(CATEGORIA, "Http.downloadArquivo: " + url);
		
		//Authentication
 		Authenticator.setDefault(new Authenticator() {
 			protected PasswordAuthentication getPasswordAuthentication() {
 				PasswordAuthentication pa = new PasswordAuthentication (ServerConstants.username, ServerConstants.password.toCharArray());
				return pa;
			}
 		});
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
		return "";
	}

	/**
	 * Realiza o envio ao servidor
	 * @param String url
	 * @param Map params
	 * @return String url
	 */
	@Override
	public Boolean doPost(String url, Map<String,JSONArray> params) {
		try {
			String queryString = getQueryString(params);
			Log.i(CATEGORIA, "Post: "+queryString);
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
	private Boolean doPost(String u, String params) throws IOException { 
		String aux = params.toString().substring(2, params.toString().length()-1);
		Log.i(CATEGORIA, "Http.doPost: " + u + "?" + aux);
	 	DefaultHttpClient httpclient;
        final URL url = new URL(u);
        final String urlHost = url.getHost();
        final int urlPort = url.getPort()==-1?url.getDefaultPort():url.getPort();
        final String urlStr = url.toString();

        // Needed for specifying HTTP pre-emptive authentication
        HttpContext httpContext = null;

        httpclient = new DefaultHttpClient();

        // Set HTTP version
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
                protocolVersion);

        // HTTP Authentication 

            // Type of authentication
            List<String> authPrefs = new ArrayList<String>(2);
            authPrefs.add(AuthPolicy.BASIC);
            httpclient.getParams().setParameter("http.auth.scheme-pref", authPrefs);

            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(urlHost, urlPort),
                    new UsernamePasswordCredentials(ServerConstants.username, ServerConstants.password));

            //Preemptive mode
	          
            BasicHttpContext localcontext = new BasicHttpContext();
            BasicScheme basicAuth = new BasicScheme();
            localcontext.setAttribute("preemptive-auth", basicAuth);
            httpContext = localcontext;

        AbstractHttpMessage method = null;
        method = new HttpPost(urlStr);
        method.setParams(new BasicHttpParams().setParameter(urlStr, url));

        // POST/PUT method specific logic
        if (method instanceof HttpEntityEnclosingRequest) {

            HttpEntityEnclosingRequest eeMethod = (HttpEntityEnclosingRequest) method;
            
           
            
            // Create and set RequestEntity
            AbstractHttpEntity entity = new ByteArrayEntity(aux.getBytes());
			entity.setContentType("text/json;charset=UTF-8");
			eeMethod.setEntity(entity);
        }

        // Now Execute:
        HttpResponse http_res = httpclient.execute((HttpUriRequest) method,
                httpContext);
        
        final HttpEntity entity = http_res.getEntity();
        boolean answer = false;
        if(entity != null){
				InputStream is = entity.getContent();
			    String responseBody = readString(is);
			    answer = !responseBody.equals(null);
			    Log.i(CATEGORIA, "Valor de texto após read: "+responseBody);
        }
		return answer;
	}
	
	
	 
	/**
	 * Transforma o HashMap em uma query string para fazer o POST
	 * @param Map params
	 * @return String urlParams
	 * @throws IOException
	 */
	private String getQueryString(Map<String,JSONArray> params) throws IOException {
		if (params == null || params.size() == 0) {
			return null;
		}
		String urlParams = null;
		Iterator<String> e = (Iterator<String>) params.keySet().iterator();
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