package nutes.intelimed.controller.tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nutes.intelimed.communication.Http;
import nutes.intelimed.communication.ServerConstants;
import nutes.intelimed.model.tree.Answer;
import nutes.intelimed.model.tree.Edge;
import nutes.intelimed.model.tree.Node;
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
	//		final String json = "[ {  \"class\" : \"intermediate.Arvore\",  \"id\" : 1,  \"arestas\" : [ {    \"class\" : \"intermediate.Aresta\",    \"id\" : 19,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 19,      \"descricao\" : \"Nao\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 8,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 8,      \"descricao\" : \"Sim, no ultimo mes\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 13,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 13,      \"descricao\" : \"4 a 12 crises\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 2,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 2,      \"descricao\" : \"Sim\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 22,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 22,      \"descricao\" : \"Nao\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 4,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 4,      \"descricao\" : \"Nao sei\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 5,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 5,      \"descricao\" : \"Sim\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 23,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 23,      \"descricao\" : \"Nao sei\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 7,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 7,      \"descricao\" : \"Nao\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 16,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 16,      \"descricao\" : \"Nao\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 18,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 18,      \"descricao\" : \"Sim, com nebulizacoes\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 11,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 11,      \"descricao\" : \"Nenhuma\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 15,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 15,      \"descricao\" : \"Sim\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 21,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 21,      \"descricao\" : \"Sim\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 20,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 20,      \"descricao\" : \"Sim, ambos\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 6,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 6,      \"descricao\" : \"Nao\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 12,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 12,      \"descricao\" : \"1 a 3 crises\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 14,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 14,      \"descricao\" : \"Mais de 12 crises\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 9,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 9,      \"descricao\" : \"Sim, no ultimo ano\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 3,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 3,      \"descricao\" : \"Nao\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 17,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 17,      \"descricao\" : \"Sim, com sprays ou bombinhas\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 10,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 10,      \"descricao\" : \"Nao\"    }  }, {    \"class\" : \"intermediate.Aresta\",    \"id\" : 1,    \"resposta\" : {      \"class\" : \"intermediate.Resposta\",      \"id\" : 1,      \"descricao\" : \"Sim\"    }  } ],  \"nos\" : [ {    \"class\" : \"intermediate.No\",    \"id\" : 3,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 10,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 10,        \"descricao\" : \"Nao\"      }    } ],    \"descricao\" : \"Nos ultimos 12 (doze) meses, quantas crises de sibilos (chiado no peito) seu filho(a) teve?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 11,      \"descricao\" : \"Nenhuma\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 14,      \"descricao\" : \"Mais de 12 crises\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 12,      \"descricao\" : \"1 a 3 crises\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 13,      \"descricao\" : \"4 a 12 crises\"    } ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 9,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 15,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 15,        \"descricao\" : \"Sim\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 21,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 21,        \"descricao\" : \"Sim\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 8,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 8,        \"descricao\" : \"Sim, no ultimo mes\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 13,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 13,        \"descricao\" : \"4 a 12 crises\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 2,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 2,        \"descricao\" : \"Sim\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 4,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 4,        \"descricao\" : \"Nao sei\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 12,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 12,        \"descricao\" : \"1 a 3 crises\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 14,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 14,        \"descricao\" : \"Mais de 12 crises\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 9,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 9,        \"descricao\" : \"Sim, no ultimo ano\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 5,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 5,        \"descricao\" : \"Sim\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 23,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 23,        \"descricao\" : \"Nao sei\"      }    } ],    \"descricao\" : \"Asma\",    \"respostas\" : [ ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 1,    \"arestasEntrada\" : [ ],    \"descricao\" : \"Seu filho ou sua filha tosse ou tem chiado no peito mesmo quando nao esta resfriado?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 1,      \"descricao\" : \"Sim\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 10,      \"descricao\" : \"Nao\"    } ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 7,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 16,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 16,        \"descricao\" : \"Nao\"      }    } ],    \"descricao\" : \"Seu filho(a) melhora da tosse ou chiado quando usa sprays, bombinhas ou nebulizacoes?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 18,      \"descricao\" : \"Sim, com nebulizacoes\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 17,      \"descricao\" : \"Sim, com sprays ou bombinhas\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 20,      \"descricao\" : \"Sim, ambos\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 19,      \"descricao\" : \"Nao\"    } ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 6,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 6,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 6,        \"descricao\" : \"Nao\"      }    } ],    \"descricao\" : \"Seu filho ou sua filha vem apresentando aperto no peito ou dificuldade para respirar?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 8,      \"descricao\" : \"Sim, no ultimo mes\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 9,      \"descricao\" : \"Sim, no ultimo ano\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 7,      \"descricao\" : \"Nao\"    } ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 4,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 3,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 3,        \"descricao\" : \"Nao\"      }    } ],    \"descricao\" : \"Seu filho ou sua filha tosse ou tem chiado no peito quando se acorda?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 6,      \"descricao\" : \"Nao\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 5,      \"descricao\" : \"Sim\"    } ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 10,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 19,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 19,        \"descricao\" : \"Nao\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 20,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 20,        \"descricao\" : \"Sim, ambos\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 22,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 22,        \"descricao\" : \"Nao\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 7,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 7,        \"descricao\" : \"Nao\"      }    }, {      \"class\" : \"intermediate.Aresta\",      \"id\" : 18,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 18,        \"descricao\" : \"Sim, com nebulizacoes\"      }    } ],    \"descricao\" : \"Nao asma\",    \"respostas\" : [ ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 2,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 1,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 1,        \"descricao\" : \"Sim\"      }    } ],    \"descricao\" : \"Seu filho ou sua filha tem crises de tosse ou chiado no peito quando tem contato com poeira de casa?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 3,      \"descricao\" : \"Nao\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 2,      \"descricao\" : \"Sim\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 4,      \"descricao\" : \"Nao sei\"    } ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 5,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 11,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 11,        \"descricao\" : \"Nenhuma\"      }    } ],    \"descricao\" : \"Seu filho(a) tem crises de tosse ou sibilos (chiado no peito) quando faz exercicios fisicos, do tipo correr ou jogar bola?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 15,      \"descricao\" : \"Sim\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 16,      \"descricao\" : \"Nao\"    } ]  }, {    \"class\" : \"intermediate.No\",    \"id\" : 8,    \"arestasEntrada\" : [ {      \"class\" : \"intermediate.Aresta\",      \"id\" : 17,      \"resposta\" : {        \"class\" : \"intermediate.Resposta\",        \"id\" : 17,        \"descricao\" : \"Sim, com sprays ou bombinhas\"      }    } ],    \"descricao\" : \"Seu filho(a) tem crises de tosse ou chiado no peito quando tem contato com mofo?\",    \"respostas\" : [ {      \"class\" : \"intermediate.Resposta\",      \"id\" : 21,      \"descricao\" : \"Sim\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 22,      \"descricao\" : \"Nao\"    }, {      \"class\" : \"intermediate.Resposta\",      \"id\" : 23,      \"descricao\" : \"Nao sei\"    } ]  } ],  \"respostas\" : [ {    \"class\" : \"intermediate.Resposta\",    \"id\" : 11,    \"descricao\" : \"Nenhuma\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 8,    \"descricao\" : \"Sim, no ultimo mes\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 14,    \"descricao\" : \"Mais de 12 crises\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 3,    \"descricao\" : \"Nao\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 1,    \"descricao\" : \"Sim\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 5,    \"descricao\" : \"Sim\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 9,    \"descricao\" : \"Sim, no ultimo ano\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 2,    \"descricao\" : \"Sim\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 16,    \"descricao\" : \"Nao\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 10,    \"descricao\" : \"Nao\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 7,    \"descricao\" : \"Nao\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 18,    \"descricao\" : \"Sim, com nebulizacoes\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 21,    \"descricao\" : \"Sim\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 22,    \"descricao\" : \"Nao\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 6,    \"descricao\" : \"Nao\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 23,    \"descricao\" : \"Nao sei\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 17,    \"descricao\" : \"Sim, com sprays ou bombinhas\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 20,    \"descricao\" : \"Sim, ambos\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 15,    \"descricao\" : \"Sim\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 4,    \"descricao\" : \"Nao sei\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 12,    \"descricao\" : \"1 a 3 crises\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 19,    \"descricao\" : \"Nao\"  }, {    \"class\" : \"intermediate.Resposta\",    \"id\" : 13,    \"descricao\" : \"4 a 12 crises\"  } ]} ]";

			Log.i(CATEGORIA,"Texto retornado: " + json);
			update(json);
		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(),e);
		}
		
	}

	public void update(String json) throws Exception{
        tree.deleteAnswers();
        tree.deleteEdges();
        tree.deleteNodes();
        
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

                insertAnswers(respostasArray);
                
                insertEdge(noObject);
                
        }
	}


	private Node insertNode(JSONObject noObject, JSONArray respostasArray)
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
		tree.insertNode(node);
		return node;
		//tree.insertNode(noId, noDescricao, diagnostico);
	}

	private void insertAnswers(JSONArray respostasArray)
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

	private void insertEdge(JSONObject noObject) throws JSONException {
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
