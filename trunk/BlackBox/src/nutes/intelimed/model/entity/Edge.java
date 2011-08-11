package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Entidade aresta
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class Edge {
	private Long idaresta;
	private Long fk_idno;
	private Long fk_idresposta;

	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { EdgeTable.IDARESTA, EdgeTable.FK_IDNO, EdgeTable.FK_IDRESPOSTA };

	public Long getIdaresta() {
		return idaresta;
	}

	public void setIdaresta(Long idaresta) {
		this.idaresta = idaresta;
	}

	public Long getFk_idno() {
		return fk_idno;
	}

	public void setFk_idno(Long fk_idno) {
		this.fk_idno = fk_idno;
	}

	public Long getFk_idresposta() {
		return fk_idresposta;
	}

	public void setFk_idresposta(Long fk_idresposta) {
		this.fk_idresposta = fk_idresposta;
	}

	/**
	 * Classe interna necessária para Content Provider de arestas
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 */
	public static final class EdgeTable implements BaseColumns {

		private EdgeTable() {
		}

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/arestas");

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.arestas";

		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.arestas";

		public static final String DEFAULT_SORT_ORDER = "idaresta ASC";

		public static final String IDARESTA = "idaresta";
		public static final String FK_IDNO = "fk_idno";
		public static final String FK_IDRESPOSTA = "fk_idresposta";

		/**
		 * Método que constrói uma Uri para uma aresta específica, com seu id
		 * @author Jamilson Batista (jamilsonbatista@gmail.com)
		 * @author Dyego Carlos (dyego12345@gmail.com)
		 * @param id - identificador da aresta
		 * @return uriEdge
		 */
		public static Uri getUriId(long id) {
			Uri uriEdge = ContentUris.withAppendedId(EdgeTable.CONTENT_URI,id);
			return uriEdge;
		}
	}

}
