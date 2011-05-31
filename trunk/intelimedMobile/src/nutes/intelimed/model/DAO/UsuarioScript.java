package nutes.intelimed.model.DAO;

import android.content.Context;
import android.util.Log;

public class UsuarioScript extends UsuarioDao{

	// Script para fazer drop na tabela
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
		"DROP TABLE IF EXISTS permissao;",
		"DROP TABLE IF EXISTS grupo;",
		"DROP TABLE IF EXISTS usuario;",
		"DROP TABLE IF EXISTS grupo_permissao;"
	};

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table permissao(_id integer primary key autoincrement, nome varchar(45), descricao varchar(45));",
			"create table grupo (_id integer primary key autoincrement, nome varchar(45), descricao varchar(45));",
			"create table usuario ( _id integer primary key autoincrement, user text not null,password text not null,id_grupo integer, Foreign Key (id_grupo) references grupo(_id));",
			"create table grupo_permissao (id_grupo integer, id_permissao integer, Foreign Key (id_grupo) references grupo(_id), Foreign Key (id_permissao) references permissao(_id));",
			"insert into permissao (nome,descricao) values('deletar','paciente');",
			"insert into grupo (nome,descricao) values('acs','Agentes de saúde');",
			"insert into usuario (user,password,id_grupo) values('jamilson','202cb962ac59075b964b07152d234b70', 1);"
			 };
	// Nome do banco
	private static final String NOME_BANCO = "inteliMobile";

	// Controle de versã
	private static final int VERSAO_BANCO = 1;

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
		Log.i("jamilson", "Mandou fechar o banco");
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

}