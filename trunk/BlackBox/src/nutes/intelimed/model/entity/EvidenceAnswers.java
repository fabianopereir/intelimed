package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Entidade respostas de evidência
 */
public class EvidenceAnswers {
	private Long idresposta;
	private Long fk_idno;
	private Long fk_idevidencia;
	private Long resposta;

	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { EvidenceAnswersTable.IDEVIDENCIARESPOSTAS,
													EvidenceAnswersTable.FK_IDNO,
													EvidenceAnswersTable.FK_IDEVIDENCIA, 
													EvidenceAnswersTable.RESPOSTA };

	

	public Long getIdresposta() {
		return idresposta;
	}



	public void setIdresposta(Long idresposta) {
		this.idresposta = idresposta;
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



	public Long getResposta() {
		return resposta;
	}



	public void setResposta(Long resposta) {
		this.resposta = resposta;
	}



	/**
	 * 
	 * @author Jamilson Batista e Dyego Carlos
	 * @Description Classe interna necessária para Content Provider de
	 *              respostas de evidência
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
		public static final String RESPOSTA = "resposta";

		public static Uri getUriId(long id) {
			Uri uriRespostasEvidencia = ContentUris.withAppendedId(
					EvidenceAnswersTable.CONTENT_URI, id);
			return uriRespostasEvidencia;
		}
	}

}
