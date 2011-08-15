package nutes.intelimed.controller;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IBlackBox {

	public abstract String controlTree(String[] arrQuest, JSONArray arrayJason,
			JSONObject treeObj, String[] arrNO);

}
