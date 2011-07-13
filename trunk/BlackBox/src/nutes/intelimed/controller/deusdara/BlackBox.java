package nutes.intelimed.controller.deusdara;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela engine inteligente
 */
public class BlackBox {
	
	public void TreeQuestionnaire()
	{
		
	}
	
	/**
	 * 
	 * @param arrQuest, arrayJason, treeObj
	 * @return array de Strings com questões e suas respectivas respostas
	 */
	public String[] controlTree(String[] arrQuest, JSONArray arrayJason, JSONObject treeObj) {
		// TODO Auto-generated method stub
		String[] res = new String[4];
		for (int i=0;i<4;i++){
			
			System.out.println("arrQuest[i] = "+arrQuest[i]);
			
			if(arrQuest[i]=="1"){
				arrQuest[i]="a";
			}else if(arrQuest[i]=="2"){
				arrQuest[i]="b";
			}else if(arrQuest[i]=="3"){
				arrQuest[i]="c";
			}else if(arrQuest[i]=="4"){
				arrQuest[i]="d";
			}
			
			
			res[i] = "Questão "+ (i+1) +": Resposta "+ arrQuest[i]+". ";
		}
		
		System.out.println(res);
		
		return res;
	}
	
}
