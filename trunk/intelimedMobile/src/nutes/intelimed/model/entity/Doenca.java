package nutes.intelimed.model.entity;

import nutes.intelimed.model.entity.ReportSuspeita.Suspeita;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class Doenca {
	
	public long id;
	public String suspeita;
	public String doenca;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { Doencas._ID, Doencas.DOENCA };
	
	public Doenca(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return suspeita;
	}

	public void setNome(String suspeita) {
		this.suspeita = suspeita;
	}
public static final class Doencas implements BaseColumns {
		
		private Doencas() {
		}
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/doenca");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.doenca";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.doenca";
	
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
	
		public static final String DOENCA = "nome";
	
		/**
		 * Método que constrói uma Uri para um Paciente específico, com o seu id
		 */
		public static Uri getUriId(long id) {
			Uri uriDoenca = ContentUris.withAppendedId(Doencas.CONTENT_URI, id);
			return uriDoenca;
		}
	}

}