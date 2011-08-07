package nutes.intelimed.util;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Classe auxiliar para tratamento das opções de respostas
 */
public class AnswerOption {
	public String resposta;
	public int codeResposta;
	public int fk_idno;
	
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public int getCodeResposta() {
		return codeResposta;
	}
	public void setCodeResposta(int codResposta) {
		this.fk_idno = codResposta;
	}
	public int getFk_Idano() {
		return fk_idno;
	}
	public void getFk_Idano(int fk_idano) {
		this.fk_idno = fk_idano;
	}
}
