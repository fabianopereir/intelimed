package nutes.intelimed.test;


import nutes.intelimed.test.communication.HttpConnectionTest;
import nutes.intelimed.test.communication.TreeUpdateTest;
import nutes.intelimed.test.diagnostic.StructureQuestionnaireTest;
import nutes.intelimed.test.evidence.EvidenceAnswersDaoTest;
import nutes.intelimed.test.evidence.EvidenceDaoTest;
import nutes.intelimed.test.evidence.EvidenceServerDaoTest;
import nutes.intelimed.test.tree.AnswersDaoTest;
import nutes.intelimed.test.tree.EdgeDaoTest;
import nutes.intelimed.test.tree.NodeDaoTest;
import nutes.intelimed.test.user.UserDaoTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(UserDaoTest.class);
		suite.addTestSuite(AnswersDaoTest.class);
		suite.addTestSuite(NodeDaoTest.class);
		suite.addTestSuite(AnswersDaoTest.class);
		suite.addTestSuite(EdgeDaoTest.class);	
		suite.addTestSuite(HttpConnectionTest.class);
		suite.addTestSuite(EvidenceDaoTest.class);
		suite.addTestSuite(EvidenceAnswersDaoTest.class);	
		suite.addTestSuite(EvidenceServerDaoTest.class);
		suite.addTestSuite(TreeUpdateTest.class);
		suite.addTestSuite(StructureQuestionnaireTest.class);
		
		//$JUnit-END$
		return suite;
	}

}
