package nutes.intelimed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParserSimulation {

	public ParserSimulation()
	{
		
	}
	public void parserJson(String arquivo)
	{
		JSONObject teste = new JSONObject();
		try {
			
			
			//System.out.println("Propriedade1"+teste.get(arquivo));
			System.out.println("Propriedade1"+teste.getJSONObject(arquivo));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
