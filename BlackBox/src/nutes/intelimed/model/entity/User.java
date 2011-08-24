package nutes.intelimed.model.entity;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Entidade Usuário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class User {
	
	public  String Vuser;
	public String Vpassword;
	
	public static final String AUTHORITY = "nutes.intelimed.model.entity";
	public static String[] colunas = new String[] { Users._ID, Users.USUARIO, Users.SENHA };
	
	public User()
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
	
	/**
	 * Classe interna necessária para Content Provider de usuários
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 */
	public static final class Users implements BaseColumns {
		
		private Users() {
		}
	
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/usuarios");
	
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.usuarios";
	
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.usuarios";
	
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
	

		public static final String USUARIO = "user";
		public static final String SENHA = "password";
	
		/**
		 * Método que constrói uma Uri para um Usuário específico, com o seu id
		 */
		public static Uri getUriId(long id) {
			Uri uriFuncionario = ContentUris.withAppendedId(Users.CONTENT_URI, id);
			return uriFuncionario;
		}
	}
}