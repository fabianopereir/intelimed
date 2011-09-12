package nutes.intelimed;

import nutes.intelimed.service.BlackBox;
import nutes.intelimed.service.IBlackBox;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * Classe responsável por simular parser para leitura do json da árvore
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class ParserSimulation {
	protected static final String CATEGORIA = "nutes";
	
	public static IBlackBox tree;
	
	
	private JSONObject jObject;
	private String jString = "[{\"class\":\"intermediate.Arvore\",\"id\":1,\"arestas\":[{\"class\":\"intermediate.Aresta\",\"id\":1,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"}},{\"class\":\"intermediate.Aresta\",\"id\":2,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"}},{\"class\":\"intermediate.Aresta\",\"id\":3,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}},{\"class\":\"intermediate.Aresta\",\"id\":7,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}},{\"class\":\"intermediate.Aresta\",\"id\":5,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}},{\"class\":\"intermediate.Aresta\",\"id\":4,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"}},{\"class\":\"intermediate.Aresta\",\"id\":6,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"}}],\"nos\":[{\"class\":\"intermediate.No\",\"id\":1,\"arestasEntrada\":[],\"descricao\":\"Outlook\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"},{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"},{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}]},{\"class\":\"intermediate.No\",\"id\":2,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":1,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"}}],\"descricao\":\"Humidity\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"},{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}]},{\"class\":\"intermediate.No\",\"id\":3,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":3,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}}],\"descricao\":\"Wind\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"},{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}]},{\"class\":\"intermediate.No\",\"id\":4,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":2,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"}},{\"class\":\"intermediate.Aresta\",\"id\":7,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}},{\"class\":\"intermediate.Aresta\",\"id\":5,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}}],\"descricao\":\"Yes\",\"respostas\":[]},{\"class\":\"intermediate.No\",\"id\":5,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":4,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"}},{\"class\":\"intermediate.Aresta\",\"id\":6,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"}}],\"descricao\":\"No\",\"respostas\":[]}],\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"},{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"},{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"},{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"},{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"},{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"},{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}]}]";
	private String clear = jString.substring(1, jString.length()-1);
	
	private BlackBox bb;
	
	public ParserSimulation(BlackBox bb) {
		this.bb = bb;
	}

	/**
	 * Método responsável por simular parser para leitura do json
	 * @param String - arquivo json do servidor
	 * @return void
	 */
	public void parserJson(String arquivo) throws Exception {
		tree = bb;
		
		jObject = new JSONObject(clear);

		String attributeArvore = jObject.getString("class");
		Log.i(CATEGORIA,attributeArvore);
		int attributeId = jObject.getInt("id");
		Log.i(CATEGORIA,"ArvoreID: "+ attributeId+"");
		System.out.println(attributeArvore);
		
		//Arestas
		JSONArray arestasArray = jObject.getJSONArray("arestas");

		for (int i = 0; i < arestasArray.length(); i++) {
			JSONObject arestaObject = arestasArray.getJSONObject(i);
			
			Log.i(CATEGORIA,arestaObject.getString("class").toString());
			Long arestaId = arestaObject.getLong("id");
			Log.i(CATEGORIA,"ArestaID: "+arestaId);
						
			JSONObject respostaObject = arestaObject.getJSONObject("resposta");
			Log.i(CATEGORIA, respostaObject.getString("class"));
			Long respostaId = respostaObject.getLong("id");
			Log.i(CATEGORIA,"RespostaID: "+respostaId);
			String respostaDescricao = respostaObject.getString("descricao");
			Log.i(CATEGORIA,"Resposta Descrição: "+respostaDescricao);
			
			//tree.insertEdge(arestaId,respostaId,respostaDescricao);
		}
		
		//Nós
		JSONArray nosArray = jObject.getJSONArray("nos");

		for (int i = 0; i < nosArray.length(); i++) {
			JSONObject noObject = nosArray.getJSONObject(i);
			
			Log.i(CATEGORIA,noObject.getString("class").toString());
			Long noId = noObject.getLong("id");
			Log.i(CATEGORIA,"NoID: "+noId);
			String noDescricao = noObject.getString("descricao");
			Log.i(CATEGORIA,"No Descrição: "+noDescricao);

			tree.insertNode(noId, noDescricao);

			
			JSONArray arestasNoArray = noObject.getJSONArray("arestasEntrada");
			
			for (int k = 0; k < arestasNoArray.length(); k++) {
				JSONObject arestaObject = arestasNoArray.getJSONObject(k);
				
				Log.i(CATEGORIA,arestaObject.getString("class").toString());
				Long arestaId = arestaObject.getLong("id");
				Log.i(CATEGORIA,"ArestaID: "+arestaId);
							
				JSONObject respostaObject = arestaObject.getJSONObject("resposta");
				Log.i(CATEGORIA, respostaObject.getString("class"));
				Long respostaId = respostaObject.getLong("id");
				Log.i(CATEGORIA,"RespostaID: "+respostaId);
				String respostaDescricao = respostaObject.getString("descricao");
				Log.i(CATEGORIA,"Resposta Descrição: "+respostaDescricao);
				
				tree.insertNodeEdge(arestaId,respostaId);
			}
			
			
			JSONArray respostasArray = noObject.getJSONArray("respostas");
			
			for (int j = 0; j < respostasArray.length(); j++) {
				JSONObject respostaObject = respostasArray.getJSONObject(j);
				Log.i(CATEGORIA,respostaObject.getString("class"));
				Long respostaId = respostaObject.getLong("id");
				Log.i(CATEGORIA,"RespostaID: "+respostaId);
				String respostaDescricao = respostaObject.getString("descricao");
				Log.i(CATEGORIA,"Resposta Descrição: "+respostaDescricao);
				
				
				tree.insertNodeAnswers(respostaId, respostaDescricao);
			}
			
			
		}
		
		//Respostas
		JSONArray respostasArray = jObject.getJSONArray("respostas");

		for (int i = 0; i < respostasArray.length(); i++) {
			JSONObject respostaObject = respostasArray.getJSONObject(i);
			Log.i(CATEGORIA,respostaObject.getString("class"));
			Long respostaId = respostaObject.getLong("id");
			Log.i(CATEGORIA,"RespostaID: "+respostaId);
			String respostaDescricao = respostaObject.getString("descricao");
			Log.i(CATEGORIA,"Resposta Descrição: "+respostaDescricao);
			
			//tree.insertAnswers(respostaId, respostaDescricao);
		}
	}
}
