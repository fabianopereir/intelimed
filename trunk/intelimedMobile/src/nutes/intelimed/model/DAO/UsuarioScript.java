package nutes.intelimed.model.DAO;

import android.content.Context;
import android.util.Log;

public class UsuarioScript extends UsuarioDao{

	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS usuario";

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table usuario ( _id integer primary key autoincrement, user text not null,password text not null);",
			"insert into usuario (user,password) values('jamilson','202cb962ac59075b964b07152d234b70');"
			 };

	// Nome do banco
	private static final String NOME_BANCO = "inteliMobile";

	// Controle de versão
	private static final int VERSAO_BANCO = 2;

	// Nome da tabela
	public static final String NOME_TABELA = "usuario";

	// Classe utilitária para abrir, criar, e atualizar o banco de dados
	private SQLiteHelper dbHelper;

	/**
	 * Cria o banco de dados com um script SQL
	 */
	
	public UsuarioScript(Context ctx) {
		
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, UsuarioScript.NOME_BANCO, UsuarioScript.VERSAO_BANCO,
				UsuarioScript.SCRIPT_DATABASE_CREATE, UsuarioScript.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar também
		db = dbHelper.getWritableDatabase();
		Log.i("jamilson", "passou2");
	}

	/**
	 * Fecha o banco
	 */
	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

}