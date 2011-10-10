package nutes.intelimed.controller.diagnostic;

import java.util.List;

import nutes.intelimed.model.diagnostic.StructureQuestionnaire;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Interface de BlackBox
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)	
 * 
 */
public interface IDiagnostic {

	public abstract String controlTree(String[] arrQuest, JSONArray arrayJason,JSONObject treeObj, String[] arrNO);
	public List<StructureQuestionnaire> listarEstruturaQuestionario();
}
