package nutes.intelimed.model.diagnostic;

/**
 * Classe útil para o tratamento das opções de respostas de cada pergunta
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class AnswerOption {
	private String resposta;
	private int codeResposta;
	private int idNo;
	
	public AnswerOption(String resposta, int codeResposta, int idNo) {
		this.resposta = resposta;
		this.codeResposta = codeResposta;
		this.idNo = idNo;
	}
	
	public AnswerOption() {
	}
	
	public String getResposta() {
		return resposta;
	}
	
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getCodeResposta() {
		return codeResposta;
	}

	public void setCodeResposta(int codeResposta) {
		this.codeResposta = codeResposta;
	}

	public int getIdNo() {
		return idNo;
	}

	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}
}
