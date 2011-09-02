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
 * Classe respons�vel por realizar upload de evid�ncias ao servidor
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class SendEvidence extends Thread implements Runnable{

	private final String CATEGORIA = "nutes";
	public static String url;
	public static Map params;
	
	private IModelEvidenceDao daoEvidence;
	private IModelEvidenceAnswersDao daoEvidenceAnswer;
	private Context ctx;
	
	/**
	 * M�todo construtor
	 * @param Context - contexto
	 */
	public SendEvidence(Context ctx){
		this.ctx = ctx;
		daoEvidence = (IModelEvidenceDao) new EvidenceScript(ctx);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersScript(ctx);
	}
	
	/**
	 * Faz upload de evid�ncias para servidor
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
	}

}