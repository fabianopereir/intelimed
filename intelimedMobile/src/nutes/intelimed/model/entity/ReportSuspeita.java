package nutes.intelimed.model.entity;

import nutes.intelimed.model.entity.Paciente.Pacientes;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ReportSuspeita {
	public long id;
	public String suspeita;
	public String doenca;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { Suspeita._ID, Suspeita.SUSPEITA };
	
	public ReportSuspeita(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getSupeita() {
		return suspeita;
	}

	public void setSupeita(String suspeita) {
		this.suspeita = suspeita;
	}
	
	public String getDoenca() {
		return suspeita;
	}

	public void setDoenca(String doenca) {
		this.doenca = doenca;
	}
	
public static final class Suspeita implements BaseColumns {
		
		private Suspeita() {
		}
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/report_suspeita");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.report_suspeita";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.report_suspeita";
	
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
	
		public static final String SUSPEITA = "suspeita";
	
		/**
		 * Método que constrói uma Uri para um Paciente específico, com o seu id
		 */
		public static Uri getUriId(long id) {
			Uri uriSuspeita = ContentUris.withAppendedId(Suspeita.CONTENT_URI, id);
			return uriSuspeita;
		}
	}

}
