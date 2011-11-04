package nutes.intelimed.controller.evidence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import nutes.intelimed.communication.ServerConstants;
import nutes.intelimed.model.evidence.Evidence;
import nutes.intelimed.model.evidence.EvidenceAnswers;
import nutes.intelimed.model.evidence.EvidenceAnswersDao;
import nutes.intelimed.model.evidence.EvidenceDao;
import nutes.intelimed.model.evidence.EvidenceServer;
import nutes.intelimed.model.evidence.EvidenceServerDao;
import nutes.intelimed.model.evidence.IModelEvidenceAnswersDao;
import nutes.intelimed.model.evidence.IModelEvidenceDao;
import nutes.intelimed.model.evidence.IModelEvidenceServerDao;

public class EvidenceController implements IEvidence{
	private IModelEvidenceDao daoEvidence;
	private IModelEvidenceAnswersDao daoEvidenceAnswer;
	private IModelEvidenceServerDao daoEvidenceToServer;
	private Context ctx;
	
	public EvidenceController(Context ctx){
		daoEvidence = (IModelEvidenceDao) new EvidenceDao(ctx);
		daoEvidenceAnswer = (IModelEvidenceAnswersDao) new EvidenceAnswersDao(ctx);
		
		daoEvidenceToServer = (IModelEvidenceServerDao) new EvidenceServerDao(ctx);
		this.ctx = ctx;
	}

	public void storeEvidence(String[] answerData, String[] noData, Evidence evidence) {
		Long idevidencia = daoEvidence.insertEvidence(evidence);
		EvidenceAnswers evidenceAnswer = new EvidenceAnswers();
		for (int i = 0; i < answerData.length; i++) {
			if (noData[i] != null && answerData[i] != null) {
				evidenceAnswer.setFk_idevidencia(idevidencia);
				evidenceAnswer.setFk_idResposta(Long.parseLong(answerData[i]));
				daoEvidenceAnswer.insertEvidenceAnswers(evidenceAnswer);
			}
		}
	}

	public void sendEvidence() throws JSONException {
		 Map<String, JSONArray> params = makeJson();
		 
		 startSendEvidence(params);
	}

	public Map<String, JSONArray> makeJson() throws JSONException {
		ArrayList<EvidenceServer> arrayData = (ArrayList<EvidenceServer>) daoEvidenceToServer.searchEvidenceToServer();
		 int aux;
         EvidenceServer evidenceToServer;
         
		 JSONObject data = new JSONObject();
		 JSONObject dataEvidence;
		 JSONObject dataAnswer;
		 JSONArray arrData = new JSONArray();
		 JSONArray arrAnswer;

		 for (int i = 0; i < arrayData.size(); i++) {
             aux = i;
             evidenceToServer = arrayData.get(aux);
             
             arrAnswer = new JSONArray();
             dataEvidence = new JSONObject();
			 
			 dataEvidence.put("idevidencia", arrayData.get(i).getIdevidencia());
			 dataEvidence.put("sistema", arrayData.get(i).getSistema());
			 dataEvidence.put("medico", arrayData.get(i).getMedico());
			 dataEvidence.put("justificativa", arrayData.get(i).getJustificativa());
			 
             while (arrayData.get(i).getIdevidencia() == evidenceToServer.getIdevidencia() && aux < arrayData.size()) {
                     
	            	 dataAnswer = new JSONObject();
	    			 dataAnswer.put("fk_idno", arrayData.get(aux).getFk_idno());
	    			 dataAnswer.put("idresposta", arrayData.get(aux).getIdresposta());
	    			 arrAnswer.put(dataAnswer);
	    			
                     aux++;
                     if (aux < arrayData.size()) {
                             evidenceToServer = arrayData.get(aux);
                     }
             }
             dataEvidence.put("respostas", arrAnswer);
			 arrData.put(dataEvidence);
            
             i = aux - 1;
         }
		 data.put("dados", arrData);
		 System.out.println("Dados Mobile: "+data);
		 Map<String,JSONArray> params = new HashMap<String, JSONArray>();
		 params.put("n1", arrData);
		return params;
	}

	private void startSendEvidence(Map<String, JSONArray> params) {
		SendEvidence sEv = new SendEvidence(this.ctx);
		 sEv.setUrl(ServerConstants.getContextFromPost());
		 sEv.setParams(params);
		 sEv.run();
	}

	public ArrayList<EvidenceServer> searchEvidenceToServer() {
		return this.daoEvidenceToServer.searchEvidenceToServer();
	}

	public long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers) {
		return this.daoEvidenceAnswer.insertEvidenceAnswers(evidenceAnswers);
	}

	public boolean deleteEvidenceAnswers() {
		return this.daoEvidenceAnswer.deleteEvidenceAnswers();
	}

	public long insertEvidence(Evidence evidence) {
		return this.daoEvidence.insertEvidence(evidence);
	}

	public boolean deleteEvidence() {
		return this.daoEvidence.deleteEvidence();
	}
}
