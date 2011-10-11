package nutes.intelimed.model.tree;

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
	
	public Edge(){
		
	}
	
	public Edge(Long idaresta, Long fk_idno, Long fk_idresposta) {
		this.idaresta = idaresta;
		this.fk_idno = fk_idno;
		this.fk_idresposta = fk_idresposta;
	}

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
	public static final class EdgesTableConstants {
		
		public static final String IDARESTA = "idaresta";
		public static final String FK_IDNO = "fk_idno";
		public static final String FK_IDRESPOSTA = "fk_idresposta";
		
		public static String[] colunas = new String[] { EdgesTableConstants.IDARESTA, EdgesTableConstants.FK_IDNO, EdgesTableConstants.FK_IDRESPOSTA };
		
		private EdgesTableConstants() {
			
		}
		
	}

}
