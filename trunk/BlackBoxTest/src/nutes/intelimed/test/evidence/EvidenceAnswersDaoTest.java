package nutes.intelimed.test.evidence;

import nutes.intelimed.activity.Login;
import nutes.intelimed.model.evidence.Evidence;
import nutes.intelimed.model.evidence.EvidenceAnswers;
import nutes.intelimed.model.evidence.EvidenceAnswersDao;
import nutes.intelimed.model.evidence.EvidenceDao;
import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.AnswersDao;
import android.test.ActivityInstrumentationTestCase2;

public class EvidenceAnswersDaoTest extends ActivityInstrumentationTestCase2<Login> {
	
	EvidenceAnswers var1;
	EvidenceAnswers var2;
	EvidenceAnswers var3;
	Evidence ev1;
	Evidence ev2;
	Evidence ev3;
	Answer a1;
	Answer a2;
	Answer a3;
	EvidenceAnswersDao dao;
	EvidenceDao daoEv;
	AnswersDao daoAnswers;

	public EvidenceAnswersDaoTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dao = new EvidenceAnswersDao(getActivity().getBaseContext());
		daoEv = new EvidenceDao(getActivity().getBaseContext());
		daoAnswers = new AnswersDao(getActivity().getBaseContext());
		
		a1 = new Answer(new Long(1),new Long(20),"Teste1",new Long(5));
		a2 = new Answer(new Long(2),new Long(21),"Teste2",new Long(5));
		a3 = new Answer(new Long(2),new Long(22),"Teste3",new Long(5));
		
		daoAnswers.insertAnswer(a1);
		daoAnswers.insertAnswer(a2);
		daoAnswers.insertAnswer(a3);
		
		ev1 = new Evidence(null,"Yes","No","teste1");
		ev2 = new Evidence(null,"Yes","Yes","");
		ev3 = new Evidence(null,"Yes","No","teste2");
		
		Long id1 = daoEv.insertEvidence(ev1);
		Long id2 = daoEv.insertEvidence(ev2);
		Long id3 = daoEv.insertEvidence(ev3);
		
		var1 = new EvidenceAnswers(null, id1, new Long(20));
		var2 = new EvidenceAnswers(null, id2, new Long(21));
		var3 = new EvidenceAnswers(null, id3, new Long(22));
		
	}
	
	public void testInsert(){
		
		Long id1 = dao.insertEvidenceAnswers(var1);
		assertTrue(dao.hasEvidence(id1));
		assertFalse(dao.hasEvidence(id1+1));
		assertFalse(dao.hasEvidence(id1+2));
		
		Long id2 = dao.insertEvidenceAnswers(var2);
		assertTrue(dao.hasEvidence(id1));
		assertTrue(dao.hasEvidence(id2));
		assertFalse(dao.hasEvidence(id2+1));
		
		Long id3 = dao.insertEvidenceAnswers(var3);
		assertTrue(dao.hasEvidence(id1));
		assertTrue(dao.hasEvidence(id2));
		assertTrue(dao.hasEvidence(id3));
	}
	
	public void testDelete(){
		Long id4 = dao.insertEvidenceAnswers(var3);
		assertTrue(dao.hasEvidence(id4));
		assertEquals(true, dao.deleteEvidenceAnswers());
		assertFalse(dao.hasEvidence(id4));
	}

}
