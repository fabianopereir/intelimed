package nutes.intelimed.model.user;

import nutes.intelimed.model.ScriptConstants;
import nutes.intelimed.model.DatabaseHelper;
import nutes.intelimed.model.GenericDao;
import nutes.intelimed.model.user.User.UsersTableConstants;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

/**
* Classe responsável por realizar consultas sobre usuários no banco de dados 
* @author Jamilson Batista (jamilsonbatista@gmail.com)
* @author Dyego Carlos (dyego12345@gmail.com)
*/
public class UserDao extends GenericDao implements IModelUserDao{

	private DatabaseHelper dbHelper;
	
	public static final String NOME_TABELA = "usuario";
	
	public UserDao(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx, ScriptConstants.NOME_BANCO, ScriptConstants.VERSAO_BANCO,
				ScriptConstants.getScriptDatabaseCreate(), ScriptConstants.getScriptDatabaseDelete());
		//db = dbHelper.getWritableDatabase();
	}
	
	public User search(String user,String password) throws UserOrPasswordIncorrectException,SQLException {
		db = dbHelper.getWritableDatabase();
		Cursor c = db.query(NOME_TABELA, UsersTableConstants.colunas, UsersTableConstants.USUARIO + "='" + user + "'" + " AND " + UsersTableConstants.SENHA + "='" + password + "'", null, null, null, null);
			
		if (c.getCount() > 0) {
			c.close();
			this.fechar();
			return new User(user, password);
		}else{
			c.close();
			this.fechar();
			throw new UserOrPasswordIncorrectException();
		}	
		
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}