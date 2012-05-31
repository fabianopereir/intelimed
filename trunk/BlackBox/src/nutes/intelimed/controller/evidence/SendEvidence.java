package nutes.intelimed.controller.evidence;

import java.util.Map;

import nutes.intelimed.communication.Http;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * Classe respons�vel por realizar upload de evid�ncias ao servidor
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class SendEvidence extends Thread implements Runnable{

	private final String CATEGORIA = "nutes";
	private String url;
	private Map<String, JSONObject> params;
	private IEvidence evidences;
	
	/**
	 * M�todo construtor
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

	public Map<String,JSONObject> getParams() {
		return params;
	}

	public void setParams(Map<String,JSONObject> params) {
		this.params = params;
	}

	/**
	 * Faz upload de evid�ncias para servidor
	 * @return void
	 */	
	public void run() {	
		if (Http.getInstance().doPost(url, params)){
			if(evidences.deleteEvidenceAnswers()){
				evidences.deleteEvidence();	
			}else{
				Log.i(CATEGORIA, "Erro ao deletar tabela EvidenceAnswers");
			}
		}
	}

}
