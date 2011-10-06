package nutes.intelimed.model.entity;

/**
 * Entidade resposta
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Answer {
	private Long idNo;
	private Long idResposta;
	private String descricaoResposta;
	private Long codeResposta;
	
	public Answer(Long fk_idno, Long idresposta, String descricao_resposta,
			Long codeResposta) {
		super();
		this.idNo = fk_idno;
		this.idResposta = idresposta;
		this.descricaoResposta = descricao_resposta;
		this.codeResposta = codeResposta;
	}
	
	public Long getIdNo() {
		return idNo;
	}

	public void setIdNo(Long idNo) {
		this.idNo = idNo;
	}

	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	public String getDescricaoResposta() {
		return descricaoResposta;
	}

	public void setDescricaoResposta(String descricaoResposta) {
		this.descricaoResposta = descricaoResposta;
	}

	public Long getCodeResposta() {
		return codeResposta;
	}

	public void setCodeResposta(Long codeResposta) {
		this.codeResposta = codeResposta;
	}



	/**
	 * Classe interna com atributos da tabela respostas 
	 * 
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class AnswersTableConstants {
		
		public static final String DESCRICAO_RESPOSTA = "descricao_resposta";
		public static final String FK_IDNO = "fk_idno";
		public static final String ID_RESPOSTA = "idresposta";
		public static final String CODE_RESPOSTA = "code_resposta";
		public static String[] colunas = new String[] { AnswersTableConstants.FK_IDNO,AnswersTableConstants.ID_RESPOSTA, 
			AnswersTableConstants.CODE_RESPOSTA, AnswersTableConstants.DESCRICAO_RESPOSTA};
		
		public AnswersTableConstants(){
			
		}

	}

}