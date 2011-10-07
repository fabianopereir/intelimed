package nutes.intelimed.model.evidence;

/**
 * Entidade respostas de evidência
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceAnswers {
	private Long idevidencia_respostas;
	private Long fk_idevidencia;
	private Long fk_idresposta;

	public Long getIdevidencia_respostas() {
		return idevidencia_respostas;
	}

	public void setIdevidencia_respostas(Long idevidencia_respostas) {
		this.idevidencia_respostas = idevidencia_respostas;
	}

	public Long getFk_idevidencia() {
		return fk_idevidencia;
	}

	public void setFk_idevidencia(Long fk_idevidencia) {
		this.fk_idevidencia = fk_idevidencia;
	}

	public Long getFk_idResposta() {
		return fk_idresposta;
	}

	public void setFk_idResposta(Long fk_idresposta) {
		this.fk_idresposta = fk_idresposta;
	}


	/**
	 * Classe interna necessária para Content Provider de respostas de evidência
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class EvidenceAnswersTable  {
		public static final String IDEVIDENCIARESPOSTAS = "idevidencia_respostas";
		public static final String FK_IDEVIDENCIA = "fk_idevidencia";
		public static final String FK_IDRESPOSTA = "fk_idresposta";
		
		public static String[] colunas = new String[] { EvidenceAnswersTable.IDEVIDENCIARESPOSTAS,
			EvidenceAnswersTable.FK_IDEVIDENCIA, 
			EvidenceAnswersTable.FK_IDRESPOSTA };

		private EvidenceAnswersTable() {
		
		}
	}
}
