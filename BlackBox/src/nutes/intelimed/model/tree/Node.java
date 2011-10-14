package nutes.intelimed.model.tree;

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
	
	public Node(){
		
	}

	public Node(Long idno, String descricaoNo, int diagnostico) {
		this.idno = idno;
		this.descricaoNo = descricaoNo;
		this.diagnostico = diagnostico;
	}
	
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
	
	@Override
	public boolean equals(Object obj){
		Node node = (Node) obj;
		return (this.diagnostico==node.getDiagnostico() && this.descricaoNo.equals(node.getDescricaoNo())
			&& this.idno.equals(node.getIdno()));	
	}
	
	/**
	 * Classe interna necessária para Content Provider de nós
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * 
	 */
	public static final class NodesTableConstants {
		
		public static final String IDNO = "idno";
		public static final String DESCRICAO_NO = "descricao_no";
		public static final String DIAGNOSTICO = "diagnostico";
		
		public static String[] colunas = new String[] { NodesTableConstants.IDNO, NodesTableConstants.DESCRICAO_NO, NodesTableConstants.DIAGNOSTICO};
		
		private NodesTableConstants() {
		
		}

	}
}