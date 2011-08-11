package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

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
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { StructureQuestionnaireAll.IDNO,
													StructureQuestionnaireAll.DESCRICAO_NO,
													StructureQuestionnaireAll.IDRESPOSTA,
													StructureQuestionnaireAll.CODERESPOSTA,
													StructureQuestionnaireAll.DESCRICAO_RESPOSTA,
													StructureQuestionnaireAll.FK_IDNO
													};
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

	/**
	 * Classe interna necessária para Content Provider de estrutura de questionário
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * Classe interna necessária para Content Provider de estrutura de questionário
	 */
	public static final class StructureQuestionnaireAll implements BaseColumns {
		
		private StructureQuestionnaireAll() {
		}
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/perguntas");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.perguntas";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.perguntas";
	
		public static final String DEFAULT_SORT_ORDER = "idno ASC";
	
		
		public static final String IDNO = "idno";
		public static final String DESCRICAO_NO = "descricao_no";
		public static final String IDRESPOSTA = "idresposta";
		public static final String CODERESPOSTA = "code_resposta";
		public static final String DESCRICAO_RESPOSTA = "descricao_resposta";
		public static final String FK_IDNO = "fk_idno";
		
		/**
		 * Método que constrói uma Uri para uma estrutura de questionário específica, com seu id
		 * @author Jamilson Batista (jamilsonbatista@gmail.com)
		 * @author Dyego Carlos (dyego12345@gmail.com)
		 * @param id - identificador da estrutura de questionário
		 * @return uriStructure
		 */
		public static Uri getUriId(long id) {
			Uri uriStructure = ContentUris.withAppendedId(StructureQuestionnaireAll.CONTENT_URI, id);
			return uriStructure;
		}
	}
	
}
