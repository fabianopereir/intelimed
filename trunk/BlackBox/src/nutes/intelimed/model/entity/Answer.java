package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Entidade resposta
 * 
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Answer {
	private Long  fk_idno;
	private Long  idresposta;
	private String  descricao_resposta;
	private Long  codeResposta;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { AnswersTable.FK_IDNO,AnswersTable.IDRESPOSTA, 
		AnswersTable.CODERESPOSTA, AnswersTable.DESCRICAO_RESPOSTA};

	
	public Long getFk_idno() {
		return fk_idno;
	}
	public void setFk_idno(Long fk_idno) {
		this.fk_idno = fk_idno;
	}
	public Long getIdresposta() {
		return idresposta;
	}
	public void setIdresposta(Long idresposta) {
		this.idresposta = idresposta;
	}
	public String getDescricao_resposta() {
		return descricao_resposta;
	}
	public void setDescricao_resposta(String descricao_resposta) {
		this.descricao_resposta = descricao_resposta;
	}
	public Long getCodeResposta() {
		return codeResposta;
	}
	public void setCodeResposta(Long codeResposta) {
		this.codeResposta = codeResposta;
	}
	
	/**
	 * Classe interna necessária para Content Provider de respostas
	 * 
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class AnswersTable implements BaseColumns {

		public static String getDescricao_resposta() {
			return DESCRICAO_RESPOSTA;
		}
		
		public static String getFk_idno() {
			return FK_IDNO;
		}
		
		public static String getIdresposta() {
			return IDRESPOSTA;
		}
		
		public static String getCodeResposta() {
			return CODERESPOSTA;
		}
		
	

		private AnswersTable() {
		}

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/respostas");

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.respostas";

		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.respostas";

		public static final String DEFAULT_SORT_ORDER = "idresposta ASC";

		public static final String DESCRICAO_RESPOSTA = "descricao_resposta";
		public static final String FK_IDNO = "fk_idno";
		public static final String IDRESPOSTA = "idresposta";
		public static final String CODERESPOSTA = "code_resposta";

		/**
		 * Método que constrói uma Uri para uma resposta específica, com seu id
		 * 
		 * @param id - identificador da resposta
		 * @return uriAnswers
		 */
		public static Uri getUriId(long id) {
			Uri uriAnswers = ContentUris.withAppendedId(
					AnswersTable.CONTENT_URI, id);
			return uriAnswers;
		}
	}

}