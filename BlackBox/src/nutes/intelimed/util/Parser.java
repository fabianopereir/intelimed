package nutes.intelimed.util;

import nutes.intelimed.controller.tree.ITree;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

/**
 * Classe respons�vel por simular parser para leitura do json da �rvore
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Parser {
	protected static final String CATEGORIA = "nutes";
	private ITree tree;
	private JSONObject jObject;

	//private String jString = "[{\"class\":\"intermediate.Arvore\",\"id\":1,\"arestas\":[{\"class\":\"intermediate.Aresta\",\"id\":1,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"}},{\"class\":\"intermediate.Aresta\",\"id\":2,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"}},{\"class\":\"intermediate.Aresta\",\"id\":3,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}},{\"class\":\"intermediate.Aresta\",\"id\":7,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}},{\"class\":\"intermediate.Aresta\",\"id\":5,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}},{\"class\":\"intermediate.Aresta\",\"id\":4,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"}},{\"class\":\"intermediate.Aresta\",\"id\":6,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"}}],\"nos\":[{\"class\":\"intermediate.No\",\"id\":1,\"arestasEntrada\":[],\"descricao\":\"Outlook\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"},{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"},{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}]},{\"class\":\"intermediate.No\",\"id\":2,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":1,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"}}],\"descricao\":\"Humidity\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"},{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}]},{\"class\":\"intermediate.No\",\"id\":3,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":3,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}}],\"descricao\":\"Wind\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"},{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}]},{\"class\":\"intermediate.No\",\"id\":4,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":2,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"}},{\"class\":\"intermediate.Aresta\",\"id\":7,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}},{\"class\":\"intermediate.Aresta\",\"id\":5,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}}],\"descricao\":\"Yes\",\"respostas\":[]},{\"class\":\"intermediate.No\",\"id\":5,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":4,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"}},{\"class\":\"intermediate.Aresta\",\"id\":6,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"}}],\"descricao\":\"No\",\"respostas\":[]}],\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"},{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"},{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"},{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"},{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"},{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"},{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}]}]";
	 
	public Parser(ITree tree) {
		this.tree = tree;
	}


	/**
	 * M�todo respons�vel por simular parser para leitura do json
	 * @param String - arquivo json do servidor
	 * @return void
	 */
	public void parserJson(String arquivo) throws Exception {

		String strClear = arquivo.substring(1, arquivo.length()-1);
		jObject = new JSONObject(strClear);


		String attributeArvore = jObject.getString("class");
		Log.i(CATEGORIA,attributeArvore);
		int attributeId = jObject.getInt("id");
		Log.i(CATEGORIA,"ArvoreID: "+ attributeId+"");
		System.out.println(attributeArvore);
		
		//Arestas
		/*JSONArray arestasArray = jObject.getJSONArray("arestas");

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
			Log.i(CATEGORIA,"Resposta Descri��o: "+respostaDescricao);
			
			//tree.insertEdge(arestaId,respostaId,respostaDescricao);
		}*/
		
		//N�s
		JSONArray nosArray = jObject.getJSONArray("nos");

		for (int i = 0; i < nosArray.length(); i++) {
			JSONObject noObject = nosArray.getJSONObject(i);
			
			Log.i(CATEGORIA,noObject.getString("class").toString());
			Long noId = noObject.getLong("id");
			Log.i(CATEGORIA,"NoID: "+noId);
			String noDescricao = noObject.getString("descricao");
			Log.i(CATEGORIA,"No Descri��o: "+noDescricao);
			
			JSONArray respostasArray = noObject.getJSONArray("respostas");
			
			boolean diagnostico;
			if(respostasArray.length()==0){
				diagnostico = true;
			}else diagnostico = false;
			
			tree.insertNode(noId, noDescricao, diagnostico);

			for (int j = 0; j < respostasArray.length(); j++) {
				JSONObject respostaObject = respostasArray.getJSONObject(j);
				Log.i(CATEGORIA,respostaObject.getString("class"));
				Long respostaId = respostaObject.getLong("id");
				Log.i(CATEGORIA,"RespostaID: "+respostaId);
				String respostaDescricao = respostaObject.getString("descricao");
				Log.i(CATEGORIA,"Resposta Descri��o: "+respostaDescricao);
				
				Long codeResposta = (long) j+1;
				tree.insertNodeAnswers(respostaId, respostaDescricao, codeResposta);
			}
			
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
				Log.i(CATEGORIA,"Resposta Descri��o: "+respostaDescricao);
				
				tree.insertNodeEdge(arestaId,respostaId);
			}
			
		}
		
		//Respostas
		/*JSONArray respostasArray = jObject.getJSONArray("respostas");

		for (int i = 0; i < respostasArray.length(); i++) {
			JSONObject respostaObject = respostasArray.getJSONObject(i);
			Log.i(CATEGORIA,respostaObject.getString("class"));
			Long respostaId = respostaObject.getLong("id");
			Log.i(CATEGORIA,"RespostaID: "+respostaId);
			String respostaDescricao = respostaObject.getString("descricao");
			Log.i(CATEGORIA,"Resposta Descri��o: "+respostaDescricao);
			
			//tree.insertAnswers(respostaId, respostaDescricao);
		}*/
	}
}