package nutes.intelimed.test.communication;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import nutes.intelimed.activity.Login;
import nutes.intelimed.communication.HttpConnection;
import nutes.intelimed.communication.ServerConstants;
import nutes.intelimed.controller.evidence.EvidenceController;
import android.test.ActivityInstrumentationTestCase2;

public class HttpConnectionTest extends ActivityInstrumentationTestCase2<Login> {

	private HttpConnection http;
	
	public HttpConnectionTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		http = new HttpConnection();
	}
	
	public void testPost(){
		Map<String, JSONArray> params;
		try {
			params = new EvidenceController(getActivity().getBaseContext()).makeJson();
			assertTrue(http.doPost(ServerConstants.getContextFromPost(), params));
		} catch (JSONException e) {
			fail();
		}
	}
	
	public void testExceptionPost(){
		Map<String, JSONArray> params;
		try {
			params = new EvidenceController(getActivity().getBaseContext()).makeJson();
			assertFalse(http.doPost("url", params));
		} catch (JSONException e) {
			fail();
		}
	}
	
	public void testGet(){
		assertTrue(!http.doGet(ServerConstants.getContextFromGet()).equals(""));
	}
	
	public void testExceptionGet(){
		assertEquals("",http.doGet("url"));
	}
	
}
