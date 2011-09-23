package nutes.intelimed.communication.helper;

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
 				PasswordAuthentication pa = new PasswordAuthentication (ServerConstants.getUsername(), ServerConstants.getPassword().toCharArray());
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
		Log.i(CATEGORIA, "Http.doPost: " + u + "?" + params);
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
                    new UsernamePasswordCredentials(ServerConstants.getUsername(), ServerConstants.getPassword()));

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
            AbstractHttpEntity entity = new ByteArrayEntity(params.getBytes());
			entity.setContentType("UTF-8");
			eeMethod.setEntity(entity);
        }

        // Now Execute:
        HttpResponse http_res = httpclient.execute((HttpUriRequest) method,
                httpContext);
        
        final HttpEntity entity = http_res.getEntity();
        if(entity != null){
				InputStream is = entity.getContent();
			    String responseBody = readString(is);
			    Log.i(CATEGORIA, "Valor de texto após read: "+responseBody);
        }
        //TODO: Fazer o Retorno
        return null;
	}
	
	
	 
	/**
	 * Transforma o HashMap em uma query string para fazer o POST
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
	
	
	
	/*private Boolean doPost(String url, String params) throws IOException {
	Log.i(CATEGORIA, "Http.doPost: " + url + "?" + params);
	execute(url, params);
	/*final String username = "admin";
		final String password = "123";
		Authenticator.setDefault(new Authenticator() {
		  protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication (username, password.toCharArray());
				return pa;
			}
		  });
		
		
	URL u = new URL(url);

	HttpURLConnection conn = (HttpURLConnection) u.openConnection();
	conn.setDoOutput(true);
	conn.setDoInput(true);
	conn.setRequestMethod("POST");
	
	//conn.setFixedLengthStreamingMode(params.length());
	
	params = "";
	OutputStream out = conn.getOutputStream();
	params = URLEncoder.encode(params, "UTF-8");
	byte[] bytes = params.getBytes();
	out.write(bytes);
	out.flush();
	out.close();
	
	//int rc = conn.getResponseCode();
	
	/*InputStream in = conn.getInputStream();

	String texto = readString(in);*/
	//Log.i(CATEGORIA, "Valor de texto após read: "+rc);
	/*BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	StringBuffer newData = new StringBuffer(10000);
	String s = "";
	while (null != ((s = br.readLine()))) {
	newData.append(s);
	} 
	
	
	conn.disconnect();*/
	/*	
	URL u = new URL(url);

	HttpURLConnection conn = (HttpURLConnection) u.openConnection();
	conn.setRequestMethod("POST");
	
	String encodedCredential = URLEncoder.encode("admin:123" , "UTF-8");
    conn.setRequestProperty("Authorization", "BASIC " + encodedCredential);
        
	conn.setDoOutput(true);
	//conn.setDoInput(true);
	
	
	//conn.setFixedLengthStreamingMode(params.length());
	
	conn.connect();
	OutputStream out = conn.getOutputStream();
	params = URLEncoder.encode(params, "UTF-8");
	byte[] bytes = params.getBytes();
	out.write(bytes);
	out.flush();
	out.close();
	 
	
	
	/*InputStream in = conn.getInputStream();

	String texto = readString(in);*/
	/*int rc = conn.getResponseCode();
	Log.i(CATEGORIA, "Valor de texto após read: "+rc);
	
	
	conn.disconnect();
	
	*/
   /*String texto = "teste";
	
	return Boolean.valueOf(texto);
}*/
	 
	 
}