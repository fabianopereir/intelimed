package nutes.intelimed.model.entity;

import android.R.integer;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Entidade evidência
 */
public class Evidence {
	private Long idevidencia;
	private String sistema;
	private String medico;
	private String justificativa;

	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { EvidenceTable.IDEVIDENCIA,
													EvidenceTable.MEDICO, 
													EvidenceTable.SISTEMA,
													EvidenceTable.JUSTIFICATIVA };

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
	 * 
	 * @author Jamilson Batista e Dyego Carlos
	 * @Description Classe interna necessária para Content Provider de
	 *              evidências
	 */
	public static final class EvidenceTable implements BaseColumns {

		private EvidenceTable() {
		}

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/evidencias");

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.evidencias";

		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.evidencias";

		public static final String DEFAULT_SORT_ORDER = "idevidencia ASC";

		public static final String IDEVIDENCIA = "idevidencia";
		public static final String SISTEMA = "sistema";
		public static final String MEDICO = "medico";
		public static final String JUSTIFICATIVA = "justificativa";

		public static Uri getUriId(long id) {
			Uri uriEvidence = ContentUris.withAppendedId(
					EvidenceTable.CONTENT_URI, id);
			return uriEvidence;
		}
	}

}
