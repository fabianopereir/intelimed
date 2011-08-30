package nutes.intelimed.communication;

import java.util.Map;

import android.util.Log;

import nutes.intelimed.communication.helper.Http;

public class SendEvidence extends Thread implements Runnable{

	private final String CATEGORIA = "nutes";
	public static String url;
	public static Map params;
	
	
	@Override
	public void run() {
		
		final String rData = Http.getInstance(Http.NORMAL).doPost(url, params);
		Log.i(CATEGORIA, "Http.doPost: " +rData);
		if (rData=="true")
		{
			
		}
	}

}
