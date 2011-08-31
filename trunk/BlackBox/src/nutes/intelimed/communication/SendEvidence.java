package nutes.intelimed.communication;

import java.util.Map;

import nutes.intelimed.InteliMEDContext;
import nutes.intelimed.communication.helper.Http;
import nutes.intelimed.model.EdgeScript;
import nutes.intelimed.model.EvidenceAnswersScript;
import nutes.intelimed.model.EvidenceScript;

import nutes.intelimed.model.DAO.IModelEvidenceAnswersDao;
import nutes.intelimed.model.DAO.IModelEvidenceDao;
import nutes.intelimed.model.NodeScript;
import android.content.Context;
import android.util.Log;

public class SendEvidence extends Thread implements Runnable{

	private final String CATEGORIA = "nutes";
	public static String url;
	public static Map params;
	
	private IModelEvidenceDao daoEvidence;
	private IModelEvidenceAnswersDao daoEvidenceAnswer;
	private Context ctx;
	
	public SendEvidence(Context ctx){
		this.ctx = ctx;
		daoEvidence = (IModelEvidenceDao) new EvidenceScript(ctx);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersScript(ctx);
	}
	
	@Override
	public void run() {

		daoEvidence = (IModelEvidenceDao) new EvidenceScript(ctx);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersScript(ctx);		

		final Boolean rData = Http.getInstance(Http.NORMAL).doPost(url, params);

		if (rData)
		{
			boolean delEvAns = daoEvidenceAnswer.deleteEvidenceAnswers();
			
			if(delEvAns){
				daoEvidence.deleteEvidence();	
			}else{
				Log.i(CATEGORIA, "Erro ao deletar tabela EvidenceAnswers");
			}
			
			
			
			Log.i(CATEGORIA, "Funcionou: " +rData);
		}
		Log.i(CATEGORIA, "Http.doPost: " +rData);
		
		
		/*if (rData!=null){
			boolean delEvAns = daoEvidenceAnswer.deleteEvidenceAnswers();
			
			if(delEvAns){
				daoEvidence.deleteEvidence();	
			}else{
				Log.i(CATEGORIA, "Erro ao deletar tabela Evidence");
			}
			
		}else{
			Log.i(CATEGORIA, "Erro ao deletar tabela EvidenceAnswers");
		}*/
	}

}
