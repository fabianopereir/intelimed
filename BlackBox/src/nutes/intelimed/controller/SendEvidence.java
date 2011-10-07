package nutes.intelimed.controller;

import java.util.Map;

import nutes.intelimed.communication.Http;
import nutes.intelimed.model.evidence.EvidenceAnswersDao;
import nutes.intelimed.model.evidence.EvidenceDao;
import nutes.intelimed.model.evidence.IModelEvidenceAnswersDao;
import nutes.intelimed.model.evidence.IModelEvidenceDao;
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
	private Map params;
	private IModelEvidenceDao daoEvidence;
	private IModelEvidenceAnswersDao daoEvidenceAnswer;
	private Context ctx;
	
	/**
	 * M�todo construtor
	 * @param Context - contexto
	 */
	public SendEvidence(Context ctx){
		this.ctx = ctx;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}


	/**
	 * Faz upload de evid�ncias para servidor
	 * @return void
	 */	
	public void run() {
		
		daoEvidence = (IModelEvidenceDao) new EvidenceDao(ctx);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersDao(ctx);		

		final Boolean rData = Http.getInstance().doPost(url, params);

		if (rData)
		{
			boolean delEvAns = daoEvidenceAnswer.deleteEvidenceAnswers();
			
			if(delEvAns){
				daoEvidence.deleteEvidence();	
			}else{
				Log.i(CATEGORIA, "Erro ao deletar tabela EvidenceAnswers");
			}
		}
		daoEvidence.fechar();
	    daoEvidenceAnswer.fechar();
	}


}
