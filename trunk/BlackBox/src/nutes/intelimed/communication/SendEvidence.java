package nutes.intelimed.communication;

import java.util.Map;

import nutes.intelimed.InteliMEDContext;
import nutes.intelimed.communication.helper.Http;
import nutes.intelimed.model.EvidenceAnswersScript;
import nutes.intelimed.model.EvidenceScript;
import nutes.intelimed.model.IModelEvidence;
import nutes.intelimed.model.IModelEvidenceAnswers;
import android.util.Log;

public class SendEvidence extends Thread implements Runnable{

	private final String CATEGORIA = "nutes";
	public static String url;
	public static Map params;
	
	private IModelEvidence daoEvidence;
	private IModelEvidenceAnswers daoEvidenceAnswer;
	
	
	@Override
	public void run() {
		
		daoEvidence = (IModelEvidence) new EvidenceScript(InteliMEDContext.getInstance());
		daoEvidenceAnswer = (IModelEvidenceAnswers) new EvidenceAnswersScript(InteliMEDContext.getInstance());		
		
		final String rData = Http.getInstance(Http.NORMAL).doPost(url, params);

		Log.i(CATEGORIA, "Http.doPost: " +rData);
		
		if (rData!=null){
			boolean delEvAns = daoEvidenceAnswer.deleteEvidenceAnswers();
			
			if(delEvAns){
				boolean delEv = daoEvidence.deleteEvidence();	
			}else{
				Log.i(CATEGORIA, "Erro ao deletar tabela Evidence");
			}
			
		}else{
			Log.i(CATEGORIA, "Erro ao deletar tabela EvidenceAnswers");
		}
	}

}
