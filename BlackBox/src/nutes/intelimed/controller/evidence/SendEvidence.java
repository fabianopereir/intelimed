package nutes.intelimed.controller.evidence;

import java.util.Map;

import org.json.JSONArray;

import nutes.intelimed.communication.Http;
import android.content.Context;
import android.util.Log;

/**
 * Classe responsável por realizar upload de evidências ao servidor
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class SendEvidence extends Thread implements Runnable{

	private final String CATEGORIA = "nutes";
	private String url;
	private Map<String,JSONArray> params;
	private IEvidence evidences;
	
	/**
	 * Método construtor
	 * @param Context - contexto
	 */
	public SendEvidence(Context ctx){
		this.evidences = new EvidenceController(ctx);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String,JSONArray> getParams() {
		return params;
	}

	public void setParams(Map<String,JSONArray> params) {
		this.params = params;
	}


	/**
	 * Faz upload de evidências para servidor
	 * @return void
	 */	
	public void run() {	

		final Boolean rData = Http.getInstance().doPost(url, params);

		if (rData)
		{
			boolean delEvAns = evidences.deleteEvidenceAnswers();
			
			if(delEvAns){
				evidences.deleteEvidence();	
			}else{
				Log.i(CATEGORIA, "Erro ao deletar tabela EvidenceAnswers");
			}
		}
	}

}
