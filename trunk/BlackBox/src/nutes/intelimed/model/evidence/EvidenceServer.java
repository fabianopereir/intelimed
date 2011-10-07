package nutes.intelimed.model.evidence;

/**
 * Entidade evidência para servidor
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class EvidenceServer {
	private Long idevidencia;
	private String sistema;
	private String medico;
	private String justificativa;
	private Long idevidencia_respostas;
	private Long  fk_idno;
	private Long  idresposta;
	private Long  fk_idresposta;
	private Long  fk_idevidencia;

	public Long getIdevidencia() {
		return idevidencia;
	}

	public void setIdevidencia(Long idevidencia) {
		this.idevidencia = idevidencia;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getJustificativa() {
		return this.justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public void setIdresposta(Long idresposta) {
		this.idresposta = idresposta;
	}

	public Long getIdresposta() {
		return idresposta;
	}

	public void setFk_idno(Long fk_idno) {
		this.fk_idno = fk_idno;
	}

	public Long getFk_idno() {
		return fk_idno;
	}

	public void setIdevidencia_respostas(Long idevidencia_respostas) {
		this.idevidencia_respostas = idevidencia_respostas;
	}

	public Long getIdevidencia_respostas() {
		return idevidencia_respostas;
	}

	public void setFk_idresposta(Long fk_idresposta) {
		this.fk_idresposta = fk_idresposta;
	}

	public Long getFk_idresposta() {
		return fk_idresposta;
	}

	public void setFk_idevidencia(Long fk_idevidencia) {
		this.fk_idevidencia = fk_idevidencia;
	}

	public Long getFk_idevidencia() {
		return fk_idevidencia;
	}

	/**
	 * Classe interna necessária para Content Provider de evidências para envio ao servidor
	 * 
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class EvidenceServerTableConstants {
		
		public static final String IDEVIDENCIA = "idevidencia";
		public static final String SISTEMA = "sistema";
		public static final String MEDICO = "medico";
		public static final String JUSTIFICATIVA = "justificativa";
		public static final String IDEVIDENCIA_RESPOSTAS = "idevidencia_respostas";
		public static final String FK_IDNO = "fk_idno";
		public static final String IDRESPOSTA = "idresposta";
		public static final String FK_IDRESPOSTA = "fk_idresposta";
		public static final String FK_IDEVIDENCIA = "fk_idevidencia";

		public static String[] colunas = new String[] { EvidenceServerTableConstants.IDEVIDENCIA,
			EvidenceServerTableConstants.MEDICO, EvidenceServerTableConstants.SISTEMA,
			EvidenceServerTableConstants.JUSTIFICATIVA,EvidenceServerTableConstants.FK_IDNO,EvidenceServerTableConstants.IDEVIDENCIA_RESPOSTAS, 
			EvidenceServerTableConstants.IDRESPOSTA, EvidenceServerTableConstants.FK_IDRESPOSTA,EvidenceServerTableConstants.FK_IDEVIDENCIA};

		private EvidenceServerTableConstants() {
		
		}
	}
}
