package nutes.intelimed.model.evidence;

/**
 * Entidade evidência
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Evidence {
	private Long idevidencia;
	private String sistema;
	private String medico;
	private String justificativa;

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

	/**
	 * Classe interna necessária para Content Provider de evidências
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class EvidencesTableConstants {

		public static final String IDEVIDENCIA = "idevidencia";
		public static final String SISTEMA = "sistema";
		public static final String MEDICO = "medico";
		public static final String JUSTIFICATIVA = "justificativa";
		
		public static String[] colunas = new String[] { EvidencesTableConstants.IDEVIDENCIA,
			EvidencesTableConstants.MEDICO, 
			EvidencesTableConstants.SISTEMA,
			EvidencesTableConstants.JUSTIFICATIVA };

		private EvidencesTableConstants() {
		
		}
	}
}
