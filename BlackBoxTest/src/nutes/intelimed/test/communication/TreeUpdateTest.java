package nutes.intelimed.test.communication;

import nutes.intelimed.activity.Login;
import nutes.intelimed.controller.tree.TreeController;
import nutes.intelimed.controller.tree.TreeUpdate;
import nutes.intelimed.model.tree.AnswersDao;
import nutes.intelimed.model.tree.EdgeDao;
import nutes.intelimed.model.tree.Node;
import nutes.intelimed.model.tree.NodeDao;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

public class TreeUpdateTest extends ActivityInstrumentationTestCase2<Login> {
	
	private String json = "[{\"class\":\"intermediate.Arvore\",\"id\":1,\"arestas\":[{\"class\":\"intermediate.Aresta\",\"id\":1,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"}},{\"class\":\"intermediate.Aresta\",\"id\":2,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"}},{\"class\":\"intermediate.Aresta\",\"id\":3,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}},{\"class\":\"intermediate.Aresta\",\"id\":7,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}},{\"class\":\"intermediate.Aresta\",\"id\":5,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}},{\"class\":\"intermediate.Aresta\",\"id\":4,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"}},{\"class\":\"intermediate.Aresta\",\"id\":6,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"}}],\"nos\":[{\"class\":\"intermediate.No\",\"id\":1,\"arestasEntrada\":[],\"descricao\":\"Outlook\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"},{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"},{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}]},{\"class\":\"intermediate.No\",\"id\":2,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":1,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"}}],\"descricao\":\"Humidity\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"},{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}]},{\"class\":\"intermediate.No\",\"id\":3,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":3,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}}],\"descricao\":\"Wind\",\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"},{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}]},{\"class\":\"intermediate.No\",\"id\":4,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":2,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"}},{\"class\":\"intermediate.Aresta\",\"id\":7,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"}},{\"class\":\"intermediate.Aresta\",\"id\":5,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"}}],\"descricao\":\"Yes\",\"respostas\":[]},{\"class\":\"intermediate.No\",\"id\":5,\"arestasEntrada\":[{\"class\":\"intermediate.Aresta\",\"id\":4,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"}},{\"class\":\"intermediate.Aresta\",\"id\":6,\"resposta\":{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"}}],\"descricao\":\"No\",\"respostas\":[]}],\"respostas\":[{\"class\":\"intermediate.Resposta\",\"id\":2,\"descricao\":\"Overcast\"},{\"class\":\"intermediate.Resposta\",\"id\":6,\"descricao\":\"Strong\"},{\"class\":\"intermediate.Resposta\",\"id\":1,\"descricao\":\"Sunny\"},{\"class\":\"intermediate.Resposta\",\"id\":4,\"descricao\":\"High\"},{\"class\":\"intermediate.Resposta\",\"id\":7,\"descricao\":\"Weak\"},{\"class\":\"intermediate.Resposta\",\"id\":5,\"descricao\":\"Normal\"},{\"class\":\"intermediate.Resposta\",\"id\":3,\"descricao\":\"Rain\"}]}]";

	private TreeUpdate tree;
	private EdgeDao edgeDao;
	private NodeDao nodeDao;
	private Context ctx;
	private AnswersDao answersDao;

	public TreeUpdateTest() {
		super("nutes.intelimed.activity.login",Login.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ctx = getActivity().getBaseContext();
	}

	public void testUpdate(){
		this.answersDao = new AnswersDao(ctx);
		this.edgeDao = new EdgeDao(ctx);
		this.nodeDao = new NodeDao(ctx);
		tree = new TreeUpdate(new TreeController(edgeDao,nodeDao,answersDao,ctx));
		assertTrue(nodeDao.searchNode("Outlook")==null);
		try {
			tree.update(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(nodeDao.searchNode("Outlook")!=null);
	}

}
