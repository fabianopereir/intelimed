package nutes.intelimed.test.tree;

import android.test.ActivityInstrumentationTestCase2;
import nutes.intelimed.activity.Login;
import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.AnswersDao;

public class AnswersDaoTest extends ActivityInstrumentationTestCase2<Login> {
	
	private Answer var1;
	private Answer var2;
	private Answer var3;
	private AnswersDao dao;
	
	public AnswersDaoTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		//super.setUp();
		dao = new AnswersDao(getActivity().getBaseContext());
		var1 = new Answer(new Long(1),new Long(20),"Teste1",new Long(5));
		var2 = new Answer(new Long(2),new Long(21),"Teste2",new Long(5));
		var3 = new Answer(new Long(2),new Long(22),"Teste3",new Long(5));
	}
	
	public void testInsert(){
		assertEquals(null, dao.searchAnswer(new Long(20)));
		dao.insertAnswer(var1);
		assertEquals(var1, dao.searchAnswer(new Long(20)));
		
		assertEquals(null, dao.searchAnswer(new Long(21)));
		dao.insertAnswer(var2);
		assertEquals(var1, dao.searchAnswer(new Long(20)));
		assertEquals(var2, dao.searchAnswer(new Long(21)));
		
		assertEquals(null, dao.searchAnswer(new Long(22)));
		dao.insertAnswer(var3);
		assertEquals(var1, dao.searchAnswer(new Long(20)));
		assertEquals(var2, dao.searchAnswer(new Long(21)));
		assertEquals(var3, dao.searchAnswer(new Long(22)));
	}
	
	public void testDelete(){
		assertEquals(true, dao.deleteAnswer());
		assertEquals(null, dao.searchAnswer(new Long(20)));
		assertEquals(null, dao.searchAnswer(new Long(21)));
		assertEquals(null, dao.searchAnswer(new Long(22)));
	}

}
