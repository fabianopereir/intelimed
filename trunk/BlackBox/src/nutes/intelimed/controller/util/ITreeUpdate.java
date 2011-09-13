package nutes.intelimed.controller.util;

public interface ITreeUpdate {
	// public abstract void insertEdge(Long arestaId, Long respostaId,String
	// respostaDescricao);

	public abstract void insertNodeEdge(Long arestaId, Long respostaId);

	public abstract void insertNode(Long noId, String noDescricao, boolean diagnostico);

	public abstract void insertNodeAnswers(Long respostaNoId,
			String respostaDescricao, Long codeResposta);

	// public abstract void insertAnswers(Long respostaId, String
	// respostaDescricao);
}
