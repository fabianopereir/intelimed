package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;



public class Question {
	public  String pergunta;
	public int idmetrica;
	public long id;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { Perguntas.IDPERGUNTA, Perguntas.PERGUNTA, Perguntas.IDMETRICA };
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getIdmetrica() {
		return idmetrica;
	}

	public void setIdmetrica(int idmetrica) {
		this.idmetrica = idmetrica;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	
	public static final class Perguntas implements BaseColumns {
		
		private Perguntas() {
		}
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/perguntas");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.perguntas";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.perguntas";
	
		public static final String DEFAULT_SORT_ORDER = "idquestao ASC";
	
		public static final String IDPERGUNTA = "idquestao";	
		public static final String PERGUNTA = "pergunta";
		public static final String IDMETRICA = "fk_idmetrica";
		
	
		
		public static Uri getUriId(long id) {
			Uri uriPaciente = ContentUris.withAppendedId(Perguntas.CONTENT_URI, id);
			return uriPaciente;
		}
	}
	
}
