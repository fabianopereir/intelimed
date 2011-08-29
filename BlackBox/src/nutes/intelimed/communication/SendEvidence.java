package nutes.intelimed.communication;

import java.util.Map;

import nutes.intelimed.communication.helper.Http;

public class SendEvidence extends Thread implements Runnable{

	public static String url;
	public static Map params;
	
	@Override
	public void run() {
		
		final String rData = Http.getInstance(Http.NORMAL).doPost(url, params);
		if (rData=="true")
		{
			
		}
	}

}
