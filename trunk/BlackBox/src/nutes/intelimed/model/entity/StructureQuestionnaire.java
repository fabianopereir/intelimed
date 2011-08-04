package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description Entidade da estrutura do questionário
 */
public class StructureQuestionnaire {
	
	public long idno;
	public String descricao_no;
	public int idresposta;
	public String descricao_resposta;
	private int fk_idno;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { StructureQuestionnaireAll.IDNO,
													StructureQuestionnaireAll.DESCRICAO_NO,
													StructureQuestionnaireAll.IDRESPOSTA,
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


	public void setIdresposta(int idresposta) {
		this.idresposta = idresposta;
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
	 * 
	 * @author Jamilson Batista e Dyego Carlos
	 * @Description Classe interna necessária para Content Provider de estrutura de questionário
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
		public static final String DESCRICAO_RESPOSTA = "descricao_resposta";
		public static final String FK_IDNO = "fk_idno";
		
		/**
		 * @Description Método que constrói uma Uri para uma estrutura de questionário específico, com seu id
		 * @param id - identificador da estrutura de questionário
		 * @return uriStructure
		 */
		public static Uri getUriId(long id) {
			Uri uriStructure = ContentUris.withAppendedId(StructureQuestionnaireAll.CONTENT_URI, id);
			return uriStructure;
		}
	}
	
}
