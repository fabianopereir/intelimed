package nutes.intelimed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Classe responsável por simular parser para leitura do json da árvore
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ParserSimulation {
	private JSONObject jObject;
	
	private String jString = "[{'class':'intermediate.Arvore','id':1,'arestas':[{'class':'intermediate.Aresta','id':1,'resposta':{'class':'intermediate.Resposta','id':1,'descricao':'Sunny'}},{'class':'intermediate.Aresta','id':2,'resposta':{'class':'intermediate.Resposta','id':2,'descricao':'Overcast'}},{'class':'intermediate.Aresta','id':3,'resposta':{'class':'intermediate.Resposta','id':3,'descricao':'Rain'}},{'class':'intermediate.Aresta','id':7,'resposta':{'class':'intermediate.Resposta','id':7,'descricao':'Weak'}},{'class':'intermediate.Aresta','id':5,'resposta':{'class':'intermediate.Resposta','id':5,'descricao':'Normal'}},{'class':'intermediate.Aresta','id':4,'resposta':{'class':'intermediate.Resposta','id':4,'descricao':'High'}},{'class':'intermediate.Aresta','id':6,'resposta':{'class':'intermediate.Resposta','id':6,'descricao':'Strong'}}],'nos':[{'class':'intermediate.No','id':1,'arestasEntrada':[],'descricao':'Outlook','respostas':[{'class':'intermediate.Resposta','id':2,'descricao':'Overcast'},{'class':'intermediate.Resposta','id':1,'descricao':'Sunny'},{'class':'intermediate.Resposta','id':3,'descricao':'Rain'}]},{'class':'intermediate.No','id':2,'arestasEntrada':[{'class':'intermediate.Aresta','id':1,'resposta':{'class':'intermediate.Resposta','id':1,'descricao':'Sunny'}}],'descricao':'Humidity','respostas':[{'class':'intermediate.Resposta','id':4,'descricao':'High'},{'class':'intermediate.Resposta','id':5,'descricao':'Normal'}]},{'class':'intermediate.No','id':3,'arestasEntrada':[{'class':'intermediate.Aresta','id':3,'resposta':{'class':'intermediate.Resposta','id':3,'descricao':'Rain'}}],'descricao':'Wind','respostas':[{'class':'intermediate.Resposta','id':6,'descricao':'Strong'},{'class':'intermediate.Resposta','id':7,'descricao':'Weak'}]},{'class':'intermediate.No','id':4,'arestasEntrada':[{'class':'intermediate.Aresta','id':2,'resposta':{'class':'intermediate.Resposta','id':2,'descricao':'Overcast'}},{'class':'intermediate.Aresta','id':7,'resposta':{'class':'intermediate.Resposta','id':7,'descricao':'Weak'}},{'class':'intermediate.Aresta','id':5,'resposta':{'class':'intermediate.Resposta','id':5,'descricao':'Normal'}}],'descricao':'Yes','respostas':[]},{'class':'intermediate.No','id':5,'arestasEntrada':[{'class':'intermediate.Aresta','id':4,'resposta':{'class':'intermediate.Resposta','id':4,'descricao':'High'}},{'class':'intermediate.Aresta','id':6,'resposta:{'class':'intermediate.Resposta','id':6,'descricao':'Strong'}}],'descricao':'No','respostas':[]}],'respostas':[{'class':'intermediate.Resposta','id':2,'descricao':'Overcast'},{'class':'intermediate.Resposta','id':6,'descricao':'Strong'},{'class':'intermediate.Resposta','id':1,'descricao':'Sunny'},{'class':'intermediate.Resposta','id':4,'descricao':'High'},{'class':'intermediate.Resposta','id':7,'descricao':'Weak'},{'class':'intermediate.Resposta','id':5,'descricao':'Normal'},{'class':'intermediate.Resposta','id':3,'descricao':'Rain'}]}]";
							
	public ParserSimulation(){
		
	}
	
	/**
	 * Método responsável por simular parser para leitura do json
	 * @param String - arquivo do servidor
	 * @return void
	 */
	public void parserJson(String arquivo){
		try {
			//jObject = new JSONObject(arquivo);
			
			jObject = new JSONObject(jString);
			
			JSONObject arvoreObject = jObject.getJSONObject("intermediate.Arvore");
			String attributeId = arvoreObject.getString("id");
			System.out.println(attributeId);
			
			JSONObject arestasObject = arvoreObject.getJSONObject("arestas");
			JSONArray arestaArray = arestasObject.getJSONArray("intermediate.Aresta");
			for (int i = 0; i < 3; i++) {
				String attributeIdAresta = arestaArray.getJSONObject(i).getString("id");
				System.out.println(attributeIdAresta);
				JSONObject respostaObject = arvoreObject.getJSONObject("resposta");
				for(int j = 0; j < 2; j++){
					String attributeIdResposta = respostaObject.getString("id").toString();
					System.out.println(attributeIdResposta);
					String attributeDescricaoResposta = respostaObject.getString("descricao");
					System.out.println(attributeDescricaoResposta);
				}
			}
			
			/*
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
			*/
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	}
}
