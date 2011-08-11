package nutes.intelimed.model.entity;

import nutes.intelimed.model.entity.Edge.EdgeTable;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Entidade nó
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Node{
	
	private Long idno;
	private String descricaoNo;
	private int diagnostico;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { NodeTable.IDNO, NodeTable.DESCRICAO_NO, NodeTable.DIAGNOSTICO};
	
	public Long getIdno() {
		return idno;
	}
	public void setIdno(Long idno) {
		this.idno = idno;
	}
	public String getDescricaoNo() {
		return descricaoNo;
	}
	public void setDescricaoNo(String descricaoNo) {
		this.descricaoNo = descricaoNo;
	}
	public int getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(int diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	/**
	 * Classe interna necessária para Content Provider de nós
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class NodeTable implements BaseColumns {

		private NodeTable() {
		}

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/nos");

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.nos";

		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.nos";

		public static final String DEFAULT_SORT_ORDER = "idno ASC";

		public static final String IDNO = "idno";
		public static final String DESCRICAO_NO = "descricao_no";
		public static final String DIAGNOSTICO = "diagnostico";

		/**
		 * Método que constrói uma Uri para um nó específico, com seu id
		 * @author Jamilson Batista (jamilsonbatista@gmail.com)
		 * @author Dyego Carlos (dyego12345@gmail.com)
		 * @param id - identificador da respostas de nó
		 * @return uriNode
		 */
		public static Uri getUriId(long id) {
			Uri uriNode = ContentUris.withAppendedId(EdgeTable.CONTENT_URI,
					id);
			return uriNode;
		}
	}
}