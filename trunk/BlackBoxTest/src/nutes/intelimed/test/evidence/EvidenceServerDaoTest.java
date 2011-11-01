package nutes.intelimed.test.evidence;

import java.util.ArrayList;
import java.util.Iterator;

import nutes.intelimed.activity.Login;
import nutes.intelimed.model.evidence.Evidence;
import nutes.intelimed.model.evidence.EvidenceAnswers;
import nutes.intelimed.model.evidence.EvidenceAnswersDao;
import nutes.intelimed.model.evidence.EvidenceDao;
import nutes.intelimed.model.evidence.EvidenceServer;
import nutes.intelimed.model.evidence.EvidenceServerDao;
import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.AnswersDao;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;


public class EvidenceServerDaoTest extends ActivityInstrumentationTestCase2<Login> {

	private EvidenceServerDao daoEvidenceServerDao;
	private EvidenceDao daoEvidence;
	private AnswersDao daoAnswers;
	private EvidenceAnswersDao daoEvidenceAnswers;
	private EvidenceAnswers var1;
	private EvidenceAnswers var2;
	private EvidenceAnswers var3;
	private Evidence ev1;
	private Evidence ev2;
	private Evidence ev3;
	private Answer a1;
	private Answer a2;
	private Answer a3;

	
	public EvidenceServerDaoTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		daoEvidence = new EvidenceDao(getActivity().getBaseContext());
		daoEvidenceServerDao = new EvidenceServerDao(getActivity().getBaseContext());
		daoAnswers = new AnswersDao(getActivity().getBaseContext());
		daoEvidenceAnswers = new EvidenceAnswersDao(getActivity().getBaseContext());
		
		daoEvidence.deleteEvidence();
		daoAnswers.deleteAnswer();
		daoEvidenceAnswers.deleteEvidenceAnswers();
		
		a1 = new Answer(new Long(1),new Long(20),"Teste1",new Long(5));
		a2 = new Answer(new Long(2),new Long(21),"Teste2",new Long(5));
		a3 = new Answer(new Long(2),new Long(22),"Teste3",new Long(5));
		
		daoAnswers.insertAnswer(a1);
		daoAnswers.insertAnswer(a2);
		daoAnswers.insertAnswer(a3);
		
		ev1 = new Evidence(null,"Yes","No","teste1");
		ev2 = new Evidence(null,"Yes","Yes","");
		ev3 = new Evidence(null,"Yes","No","teste2");
		
		Long id1 = daoEvidence.insertEvidence(ev1);
		Long id2 = daoEvidence.insertEvidence(ev2);
		Long id3 = daoEvidence.insertEvidence(ev3);
		
		var1 = new EvidenceAnswers(null, id1, new Long(20));
		var2 = new EvidenceAnswers(null, id2, new Long(21));
		var3 = new EvidenceAnswers(null, id3, new Long(22));
		
		daoEvidenceAnswers.insertEvidenceAnswers(var1);
		daoEvidenceAnswers.insertEvidenceAnswers(var2);
		daoEvidenceAnswers.insertEvidenceAnswers(var3);
		
		
	}
	
	public void testSearchEvidenceToServer(){

		ArrayList<EvidenceServer> estrutura = new ArrayList<EvidenceServer>();
		
		EvidenceServer toServer = new EvidenceServer();
		toServer.setSistema("Yes");
		toServer.setMedico("No");
		toServer.setJustificativa("teste1");
		estrutura.add(toServer);
		
		toServer = new EvidenceServer();
		toServer.setSistema("Yes");
		toServer.setMedico("Yes");
		toServer.setJustificativa("");
		estrutura.add(toServer);
		
		toServer = new EvidenceServer();
		toServer.setSistema("Yes");
		toServer.setMedico("No");
		toServer.setJustificativa("teste2");
		estrutura.add(toServer);
		
		ArrayList<EvidenceServer> estrutura2 = daoEvidenceServerDao.searchEvidenceToServer();
		Iterator<EvidenceServer> it1 = estrutura.iterator();
		Iterator<EvidenceServer> it2 = estrutura2.iterator();
		while(it1.hasNext()){
			EvidenceServer ev1 = it1.next();
			EvidenceServer ev2 = it2.next();
			Log.i("Nutes", ev1.getMedico()+ev2.getMedico());
			assertTrue(ev1.equals(ev2));
		}
		
	}

}
