package nutes.intelimed.controller.evidence;

import java.util.ArrayList;

import org.json.JSONException;
import nutes.intelimed.model.evidence.Evidence;
import nutes.intelimed.model.evidence.EvidenceAnswers;
import nutes.intelimed.model.evidence.EvidenceServer;

public interface IEvidence {	
	public void storeEvidence(String[] answerData, String[] noData, Evidence evidence);
	public void sendEvidence() throws JSONException;
	public ArrayList<EvidenceServer> searchEvidenceToServer();
	public long insertEvidenceAnswers(EvidenceAnswers evidenceAnswers);
	public boolean deleteEvidenceAnswers();
	public long insertEvidence(Evidence evidence);
	public boolean deleteEvidence();
}
