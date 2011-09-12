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

	//public abstract void insertEdge(Long arestaId, Long respostaId,String respostaDescricao);
	
	public abstract void insertNodeEdge(Long arestaId, Long respostaId);
	
	public abstract void insertNode(Long noId, String noDescricao);

	public abstract void insertNodeAnswers(Long respostaNoId,String respostaDescricao);

	//public abstract void insertAnswers(Long respostaId, String respostaDescricao);

}
