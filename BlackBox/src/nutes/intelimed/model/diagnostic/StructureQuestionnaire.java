package nutes.intelimed.model.diagnostic;

/**
 * Entidade da estrutura do questionário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class StructureQuestionnaire {
	
	private long idno;
	private String descricao_no;
	private int idresposta;
	private int code_resposta;
	private String descricao_resposta;
	private int fk_idno;
	
	public long getIdno() {
		return idno;
	}

	public void setIdno(long idno) {
		this.idno = idno;
	}

	public String getDescricao_no() {
		return descricao_no;
	}

	public void setDescricao_no(String descricaoNo) {
		descricao_no = descricaoNo;
	}

	public int getIdresposta() {
		return idresposta;
	}

	public void setIdresposta(int codeResposta) {
		this.idresposta = codeResposta;
	}
	
	public int getCodeResposta() {
		return code_resposta;
	}

	public void setCodeResposta(int codeResposta) {
		this.code_resposta = codeResposta;
	}
	
	public String getDescricao_resposta() {
		return descricao_resposta;
	}

	public void setDescricao_resposta(String descricaoResposta) {
		descricao_resposta = descricaoResposta;
	}

	public int getFk_idno() {
		return fk_idno;
	}

	public void setFk_idno(int fkIdno) {
		fk_idno = fkIdno;
	}
	
	public boolean equals(StructureQuestionnaire answer){
		//StructureQuestionnaire answer = (StructureQuestionnaire) obj;
		return (this.code_resposta==answer.getCodeResposta() && this.descricao_no.equals(answer.getDescricao_no())
			&& this.descricao_resposta.equals(answer.getDescricao_resposta()));	
	}

	/**
	 * Classe interna necessária para Content Provider de estrutura de questionário
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 */
	public static final class StructureQuestionnaireTableConstants {
			
		public static final String IDNO = "idno";
		public static final String DESCRICAO_NO = "descricao_no";
		public static final String IDRESPOSTA = "idresposta";
		public static final String CODERESPOSTA = "code_resposta";
		public static final String DESCRICAO_RESPOSTA = "descricao_resposta";
		public static final String FK_IDNO = "fk_idno";
		
		public static String[] colunas = new String[] { StructureQuestionnaireTableConstants.IDNO,
			StructureQuestionnaireTableConstants.DESCRICAO_NO,
			StructureQuestionnaireTableConstants.IDRESPOSTA,
			StructureQuestionnaireTableConstants.CODERESPOSTA,
			StructureQuestionnaireTableConstants.DESCRICAO_RESPOSTA,
			StructureQuestionnaireTableConstants.FK_IDNO
			};
		
		private StructureQuestionnaireTableConstants() {
		
		}
	}	
}
