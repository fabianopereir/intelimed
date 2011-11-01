package nutes.intelimed.test.tree;

import nutes.intelimed.activity.Login;
import nutes.intelimed.model.tree.Edge;
import nutes.intelimed.model.tree.EdgeDao;
import android.test.ActivityInstrumentationTestCase2;

public class EdgeDaoTest extends ActivityInstrumentationTestCase2<Login> {
	
	private Edge var1;
	private Edge var2;
	private Edge var3;
	private EdgeDao dao;

	public EdgeDaoTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.dao = new EdgeDao(getActivity().getBaseContext());
		this.var1 = new Edge(new Long(20), new Long(1),new Long(20));
		this.var2 = new Edge(new Long(21), new Long(1),new Long(21));
		this.var3 = new Edge(new Long(22), new Long(1),new Long(22));
	}
	
	public void testInsert(){
		assertEquals(null, dao.searchEdge(new Long(20)));
		dao.insertEdge(var1);
		assertEquals(var1, dao.searchEdge(new Long(20)));
		
		assertEquals(null, dao.searchEdge(new Long(21)));
		dao.insertEdge(var2);
		assertEquals(var1, dao.searchEdge(new Long(20)));
		assertEquals(var2, dao.searchEdge(new Long(21)));
		
		assertEquals(null, dao.searchEdge(new Long(22)));
		dao.insertEdge(var3);
		assertEquals(var1, dao.searchEdge(new Long(20)));
		assertEquals(var2, dao.searchEdge(new Long(21)));
		assertEquals(var3, dao.searchEdge(new Long(22)));
	}
	
	public void testDelete(){
		assertEquals(true, dao.deleteEdge());
		assertEquals(null, dao.searchEdge(new Long(20)));
		assertEquals(null, dao.searchEdge(new Long(21)));
		assertEquals(null, dao.searchEdge(new Long(22)));
	}
}
