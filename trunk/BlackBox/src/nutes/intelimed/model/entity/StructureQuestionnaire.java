package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class StructureQuestionnaire {
	public long idestrutura_questionario;
	public int ordem;
	public int fk_iddiagnostico;
	public int fk_idquestao;
	public  String pergunta;
	public int fk_idmetrica;
	
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { StructureQuestionnaireQuestion.IDESTRUTURA_QUESTIONARIO, StructureQuestionnaireQuestion.ORDDEM,
													StructureQuestionnaireQuestion.FK_IDDIAGNOSTICO, StructureQuestionnaireQuestion.FK_IDPERGUNTA,
													StructureQuestionnaireQuestion.PERGUNTA, StructureQuestionnaireQuestion.FK_IDMETRICA};
	
	public long getIdestrutura_questionario() {
		return idestrutura_questionario;
	}
	public void setIdestrutura_questionario(long idestrutura_questionario) {
		this.idestrutura_questionario = idestrutura_questionario;
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
	public void setFk_iddiagnostico(int fk_iddiagnostico) {
		this.fk_iddiagnostico = fk_iddiagnostico;
	}
	public int getFk_idquestao() {
		return fk_idquestao;
	}
	public void setFk_idquestao(int fk_idquestao) {
		this.fk_idquestao = fk_idquestao;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public int getIdmetrica() {
		return fk_idmetrica;
	}
	public void setIdmetrica(int idmetrica) {
		this.fk_idmetrica = idmetrica;
	}
	
	public static final class StructureQuestionnaireQuestion implements BaseColumns {
		
		private StructureQuestionnaireQuestion() {
		}
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/perguntas");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.perguntas";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.perguntas";
	
		public static final String DEFAULT_SORT_ORDER = "idestrutura_questionario ASC";
	
		public static final String IDESTRUTURA_QUESTIONARIO = "idestrutura_questionario";	
		public static final String ORDDEM = "ordem";
		public static final String FK_IDDIAGNOSTICO = "fk_iddiagnostico";
		public static final String FK_IDPERGUNTA = "fk_idquestao";
		public static final String PERGUNTA = "pergunta";
		public static final String FK_IDMETRICA = "fk_idmetrica";
	
		/**
		 * Método que constrói uma Uri para um StructureQuestionnaireQuestion específico, com o seu id
		 */
		public static Uri getUriId(long id) {
			Uri uriPaciente = ContentUris.withAppendedId(StructureQuestionnaireQuestion.CONTENT_URI, id);
			return uriPaciente;
		}
	}


}
