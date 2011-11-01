package nutes.intelimed.test.evidence;

import nutes.intelimed.activity.Login;
import nutes.intelimed.model.evidence.Evidence;
import nutes.intelimed.model.evidence.EvidenceDao;
import android.test.ActivityInstrumentationTestCase2;

public class EvidenceDaoTest extends ActivityInstrumentationTestCase2<Login> {
	
	Evidence var1;
	Evidence var2;
	Evidence var3;
	EvidenceDao dao;

	public EvidenceDaoTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		dao = new EvidenceDao(getActivity().getBaseContext());
		var1 = new Evidence(null,"Yes","No","teste1");
		var2 = new Evidence(null,"Yes","Yes","");
		var3 = new Evidence(null,"Yes","No","teste2");
	}
	
	public void testInsert(){
		
		Long id1 = dao.insertEvidence(var1);
		assertTrue(dao.hasEvidence(id1));
		assertFalse(dao.hasEvidence(id1+1));
		assertFalse(dao.hasEvidence(id1+2));
		
		Long id2 = dao.insertEvidence(var2);
		assertTrue(dao.hasEvidence(id1));
		assertTrue(dao.hasEvidence(id2));
		assertFalse(dao.hasEvidence(id2+1));
		
		Long id3 = dao.insertEvidence(var3);
		assertTrue(dao.hasEvidence(id1));
		assertTrue(dao.hasEvidence(id2));
		assertTrue(dao.hasEvidence(id3));
	}
	
	public void testDelete(){
		Long id4 = dao.insertEvidence(var3);
		assertTrue(dao.hasEvidence(id4));
		assertEquals(true, dao.deleteEvidence());
		assertFalse(dao.hasEvidence(id4));
	}
	
	

}
