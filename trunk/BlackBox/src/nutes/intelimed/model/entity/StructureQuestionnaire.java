package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class StructureQuestionnaire {
	
	public long idestrutura_questionario;
	public int fk_idquestao;
	public  String pergunta;
	public int fk_idmetrica;
	public  String tipo;
	public int fk_idpadrao_validacao;
	public  String descricao;	
	public int ordem;
	public int fk_iddiagnostico;
	
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { StructureQuestionnaireAll.IDESTRUTURA_QUESTIONARIO,
													StructureQuestionnaireAll.FK_IDPERGUNTA,
													StructureQuestionnaireAll.PERGUNTA,
													StructureQuestionnaireAll.FK_IDMETRICA,
													StructureQuestionnaireAll.TIPO,
													StructureQuestionnaireAll.FK_IDPADRAO_VALIDACAO,
													StructureQuestionnaireAll.DESCRICAO,
													StructureQuestionnaireAll.ORDDEM
													//StructureQuestionnaireAll.FK_IDDIAGNOSTICO, 
													};
	
	public long getIdestrutura_questionario() {
		return idestrutura_questionario;
	}
	public void setIdestrutura_questionario(long idestruturaQuestionario) {
		idestrutura_questionario = idestruturaQuestionario;
	}
	public int getFk_idquestao() {
		return fk_idquestao;
	}
	public void setFk_idquestao(int fkIdquestao) {
		fk_idquestao = fkIdquestao;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public int getFk_idmetrica() {
		return fk_idmetrica;
	}
	public void setFk_idmetrica(int fkIdmetrica) {
		fk_idmetrica = fkIdmetrica;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getFk_idpadrao_validacao() {
		return fk_idpadrao_validacao;
	}
	public void setFk_idpadrao_validacao(int fkIdpadraoValidacao) {
		fk_idpadrao_validacao = fkIdpadraoValidacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public int getFk_iddiagnostico() {
		return fk_iddiagnostico;
	}
	public void setFk_iddiagnostico(int fkIddiagnostico) {
		fk_iddiagnostico = fkIddiagnostico;
	}
	
	public static final class StructureQuestionnaireAll implements BaseColumns {
		
		private StructureQuestionnaireAll() {
		}
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/perguntas");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.perguntas";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.perguntas";
	
		public static final String DEFAULT_SORT_ORDER = "idestrutura_questionario ASC";
	
		public static final String IDESTRUTURA_QUESTIONARIO = "estrutura.idestrutura_questionario";
		public static final String FK_IDPERGUNTA = "estrutura.fk_idquestao";
		public static final String PERGUNTA = "quest.pergunta";
		public static final String FK_IDMETRICA = "quest.fk_idmetrica";
		public static final String TIPO = "metric.tipo";
		public static final String FK_IDPADRAO_VALIDACAO = "valmetic.fk_idpadrao_validacao";
		public static final String DESCRICAO = "pval.descricao";
		public static final String ORDDEM = "estrutura.ordem";
		//public static final String FK_IDDIAGNOSTICO = "fk_iddiagnostico";
		
				
		public static Uri getUriId(long id) {
			Uri uriPaciente = ContentUris.withAppendedId(StructureQuestionnaireAll.CONTENT_URI, id);
			return uriPaciente;
		}
	}
	
}
