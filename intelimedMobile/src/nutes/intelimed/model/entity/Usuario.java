package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class Usuario {
	
	public  String Vuser;
	public String Vpassword;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { Usuarios._ID, Usuarios.USUARIO, Usuarios.SENHA };
	//public static String[] colunas = new String[] {Usuarios.USUARIO, Usuarios.SENHA};
	
	public Usuario()
	{
	}

	public  String getVuser() {
		return Vuser;
	}

	public void setVuser(String vuser) {
		Vuser = vuser;
	}

	public String getVpassword() {
		return Vpassword;
	}

	public void setVpassword(String vpassword) {
		Vpassword = vpassword;
	}
	
	public static final class Usuarios implements BaseColumns {
		
		private Usuarios() {
		}
	
	
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/usuarios");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.usuarios";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.usuarios";
	
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
	

		public static final String USUARIO = "user";
		public static final String SENHA = "password";
	
		/**
		 * Método que constrói uma Uri para um Funcionario específico, com o seu id
		 */
		public static Uri getUriId(long id) {
			Uri uriFuncionario = ContentUris.withAppendedId(Usuarios.CONTENT_URI, id);
			return uriFuncionario;
		}
	}
}