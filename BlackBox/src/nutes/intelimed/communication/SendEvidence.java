package nutes.intelimed.communication;

import java.util.Map;
import nutes.intelimed.communication.helper.Http;
import nutes.intelimed.model.EvidenceAnswersScript;
import nutes.intelimed.model.EvidenceScript;
import nutes.intelimed.model.DAO.IModelEvidenceAnswersDao;
import nutes.intelimed.model.DAO.IModelEvidenceDao;
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
	private Map params;
	private IModelEvidenceDao daoEvidence;
	private IModelEvidenceAnswersDao daoEvidenceAnswer;
	private Context ctx;
	
	/**
	 * Método construtor
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
	 * Faz upload de evidências para servidor
	 * @return void
	 */	
	public void run() {
		
		daoEvidence = (IModelEvidenceDao) new EvidenceScript(ctx);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersScript(ctx);		

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
