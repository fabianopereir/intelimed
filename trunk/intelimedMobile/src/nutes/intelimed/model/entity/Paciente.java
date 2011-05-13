package nutes.intelimed.model.entity;

import java.io.Serializable;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class Paciente implements Serializable{
	public long id;
	public  String nome;
	public String datanascimento;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { Pacientes._ID, Pacientes.NOME, Pacientes.DTNASCIMENTO };
	//public static String[] colunas = new String[] {Usuarios.USUARIO, Usuarios.SENHA};
	
	public Paciente()
	{
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}


	public static final class Pacientes implements BaseColumns {
		
		private Pacientes() {
		}
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/pacientes");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.pacientes";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.pacientes";
	
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
	
		public static final String NOME = "nome";
		public static final String DTNASCIMENTO = "dtnascimento";
	
		/**
		 * Método que constrói uma Uri para um Paciente específico, com o seu id
		 */
		public static Uri getUriId(long id) {
			Uri uriPaciente = ContentUris.withAppendedId(Pacientes.CONTENT_URI, id);
			return uriPaciente;
		}
	}
}
