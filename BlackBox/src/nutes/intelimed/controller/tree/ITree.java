package nutes.intelimed.controller.tree;

public interface ITree {

	public abstract void insertNodeEdge(Long arestaId, Long respostaId);

	public abstract void insertNode(Long noId, String noDescricao, boolean diagnostico);

	public abstract void insertNodeAnswers(Long respostaNoId,
			String respostaDescricao, Long codeResposta);
	
	public void receiveTree() throws Exception;
}
