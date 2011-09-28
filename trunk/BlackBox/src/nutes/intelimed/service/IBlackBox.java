package nutes.intelimed.service;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Interface de BlackBox
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)	
 * 
 */
public interface IBlackBox {

	public abstract String controlTree(String[] arrQuest, JSONArray arrayJason,JSONObject treeObj, String[] arrNO);

	public void fechar();
}
