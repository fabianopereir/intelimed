package nutes.intelimed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;


public class ParserSimulation {
	private JSONObject jObject;
	
	//private String jString = "{\"menu\":	{\"id\": \"file\", \"value\": \"File\", \"popup\": { \"menuitem\": [ {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"}, {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"}, {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}]}}}";
	private String jString = "{'menu':	{'id': 'file', 'value': 'File', 'popup': { 'menuitem': [ {'value': 'New', 'onclick': 'CreateNewDoc()'}, {'value': 'Open', 'onclick': 'OpenDoc()'}, {'value': 'Close', 'onclick': 'CloseDoc()'}]}}}";
	public ParserSimulation()
	{
		
	}
	public void parserJson(String arquivo)
	{
		//JSONObject teste = new JSONObject();
		try {
			
			jObject = new JSONObject(jString);

			JSONObject menuObject = jObject.getJSONObject("menu");
			String attributeId = menuObject.getString("id");
			System.out.println(attributeId);

			String attributeValue = menuObject.getString("value");
			System.out.println(attributeValue);

			JSONObject popupObject = menuObject.getJSONObject("popup");
			JSONArray menuitemArray = popupObject.getJSONArray("menuitem");

			for (int i = 0; i < 3; i++) {
				System.out.println(menuitemArray.getJSONObject(i).getString("value").toString());
				System.out.println(menuitemArray.getJSONObject(i).getString("onclick").toString());
			}

			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
}
