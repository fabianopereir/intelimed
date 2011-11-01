package nutes.intelimed.test.tree;

import nutes.intelimed.activity.Login;
import nutes.intelimed.model.tree.Node;
import nutes.intelimed.model.tree.NodeDao;
import android.test.ActivityInstrumentationTestCase2;

public class NodeDaoTest extends ActivityInstrumentationTestCase2<Login> {
	
	private Node var1;
	private Node var2;
	private Node var3;
	private NodeDao dao;

	public NodeDaoTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dao = new NodeDao(getActivity().getBaseContext());
		this.var1 = new Node(new Long(20), "Teste1", 1);
		this.var2 = new Node(new Long(21), "Teste2", 1);
		this.var3 = new Node(new Long(22), "Teste3", 1);
	}
	
	public void testInsert(){
		assertEquals(null, dao.searchNode(new Long(20)));
		dao.insertNode(var1);
		assertEquals(var1, dao.searchNode(new Long(20)));
		
		assertEquals(null, dao.searchNode(new Long(21)));
		dao.insertNode(var2);
		assertEquals(var1, dao.searchNode(new Long(20)));
		assertEquals(var2, dao.searchNode(new Long(21)));
		
		assertEquals(null, dao.searchNode(new Long(22)));
		dao.insertNode(var3);
		assertEquals(var1, dao.searchNode(new Long(20)));
		assertEquals(var2, dao.searchNode(new Long(21)));
		assertEquals(var3, dao.searchNode(new Long(22)));
	}
	
	public void testDelete(){
		assertEquals(true, dao.deleteNode());
		assertEquals(null, dao.searchNode(new Long(20)));
		assertEquals(null, dao.searchNode(new Long(21)));
		assertEquals(null, dao.searchNode(new Long(22)));
	}

}
