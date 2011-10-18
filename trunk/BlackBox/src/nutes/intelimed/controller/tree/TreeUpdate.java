package nutes.intelimed.controller.tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nutes.intelimed.communication.Http;
import nutes.intelimed.communication.ServerConstants;
import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.Edge;
import nutes.intelimed.model.tree.Node;
import android.os.Handler;
import android.util.Log;

/**
 * Classe responsável por realizar download da árvore do servidor
 *  
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class TreeUpdate extends Thread implements Runnable {

	protected static final String CATEGORIA = "nutes";
	private Handler handler = new Handler();
	private ITree tree;
	private Edge edge;
	private Node node;

	public TreeUpdate(ITree tree) {
		this.tree = tree;
		edge = new Edge();
		node = new Node();
	}
	
	/**
	 * Faz download de um arquivo texto e atualiza a tela
	 * @return void
	 */
	public void run() {
		try {
			
			final String json = Http.getInstance().doGet(ServerConstants.getContextFromGet());

			Log.i(CATEGORIA,"Texto retornado: " + json);

			handler.post(new Runnable() {
				public void run() {
					try {
						update(json);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(),e);
		}
		
	}
	
	public void update(String json) throws Exception{
		tree.deleteAnswers();
		tree.deleteEdges();
		tree.deleteNodes();
		/*parserJason = new Parser(tree);
		parserJason.parserJson(arquivo);*/
		String strClear = json.substring(1, json.length()-1);
		JSONObject jObject = new JSONObject(strClear);

		String attributeArvore = jObject.getString("class");
		Log.i(CATEGORIA,attributeArvore);
		int attributeId = jObject.getInt("id");
		Log.i(CATEGORIA,"ArvoreID: "+ attributeId+"");
		System.out.println(attributeArvore);
		
		
		JSONArray nosArray = jObject.getJSONArray("nos");

		for (int i = 0; i < nosArray.length(); i++) {
			JSONObject noObject = nosArray.getJSONObject(i);
			
			JSONArray respostasArray = noObject.getJSONArray("respostas");
			
			insertNode(noObject, respostasArray);

			insertNodeAnswers(respostasArray);
			
			insertNodeEdge(noObject);
			
		}
	}

	private Long insertNode(JSONObject noObject, JSONArray respostasArray)
			throws JSONException {
		Log.i(CATEGORIA,noObject.getString("class").toString());
		Long noId = noObject.getLong("id");
		Log.i(CATEGORIA,"NoID: "+noId);
		String noDescricao = noObject.getString("descricao");
		Log.i(CATEGORIA,"No Descrição: "+noDescricao);
		
		int diagnostico;
		if(respostasArray.length()==0){
			diagnostico = 1;
		}else diagnostico = 0;
		node = new Node(noId,noDescricao,diagnostico);
		return tree.insertNode(node);
		//tree.insertNode(noId, noDescricao, diagnostico);
	}

	private void insertNodeAnswers(JSONArray respostasArray)
			throws JSONException {
		Answer answer;
		for (int j = 0; j < respostasArray.length(); j++) {
			JSONObject respostaObject = respostasArray.getJSONObject(j);
			Log.i(CATEGORIA,respostaObject.getString("class"));
			Long respostaId = respostaObject.getLong("id");
			Log.i(CATEGORIA,"RespostaID: "+respostaId);
			String respostaDescricao = respostaObject.getString("descricao");
			Log.i(CATEGORIA,"Resposta Descrição: "+respostaDescricao);
			
			Long codeResposta = (long) j+1;
			answer = new Answer(node.getIdno(),respostaId,respostaDescricao,codeResposta); 
			//tree.insertNodeAnswers(respostaId, respostaDescricao, codeResposta);
			tree.insertNodeAnswers(answer);
		}
	}

	private void insertNodeEdge(JSONObject noObject) throws JSONException {
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
			edge = new Edge(arestaId,node.getIdno(),respostaId);
			//tree.insertNodeEdge(arestaId,respostaId);
			tree.insertNodeEdge(edge);
		}
	}
}
