package nutes.intelimed.controller;

import org.json.JSONArray;
import org.json.JSONObject;

public class TreeQuestionnaire {

	
	
	public void TreeQuestionnaire()
	{
		
	}
	
	public String[] controlTree(String[] arrQuest, JSONArray arrayJason, JSONObject treeObj) {
		// TODO Auto-generated method stub
		String[] res = new String[4];
		for (int i=0;i<4;i++){
			res[i] = "Q["+ (i+1) +"]" + arrQuest[i];
		}
		
		System.out.println(res);
		
		return res;
	}
	
}
