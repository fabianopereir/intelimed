package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Entidade respostas de evidência
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class EvidenceAnswers {
	private Long idevidencia_respostas;
	private Long fk_idno;
	private Long fk_idevidencia;
	private Long fk_idresposta;

	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { EvidenceAnswersTable.IDEVIDENCIARESPOSTAS,
													EvidenceAnswersTable.FK_IDNO,
													EvidenceAnswersTable.FK_IDEVIDENCIA, 
													EvidenceAnswersTable.FK_IDRESPOSTA };

	

	public Long getIdvidencia_respostas() {
		return idevidencia_respostas;
	}



	public void setIdvidencia_respostas(Long idevidencia_respostas) {
		this.idevidencia_respostas = idevidencia_respostas;
	}



	public Long getFk_idno() {
		return fk_idno;
	}



	public void setFk_idno(Long fk_idno) {
		this.fk_idno = fk_idno;
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
	 * Classe interna necessária para Content Provider de
	 *              respostas de evidência
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class EvidenceAnswersTable implements BaseColumns {

		private EvidenceAnswersTable() {
		}

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/respostasEvidencia");

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.respostasEvidencia";

		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.respostasEvidencia";

		public static final String DEFAULT_SORT_ORDER = "idevidencia_respostas ASC";

		public static final String IDEVIDENCIARESPOSTAS = "idevidencia_respostas";
		public static final String FK_IDNO = "fk_idno";
		public static final String FK_IDEVIDENCIA = "fk_idevidencia";
		public static final String FK_IDRESPOSTA = "fk_idresposta";

		/**
		 * Método que constrói uma Uri para respostas de evidência, com seu id
		 * @author Jamilson Batista (jamilsonbatista@gmail.com)
		 * @author Dyego Carlos (dyego12345@gmail.com)
		 * @param id - identificador de respostas de evidência
		 * @return uriRespostasEvidencia
		 */
		public static Uri getUriId(long id) {
			Uri uriRespostasEvidencia = ContentUris.withAppendedId(
					EvidenceAnswersTable.CONTENT_URI, id);
			return uriRespostasEvidencia;
		}
	}

}
