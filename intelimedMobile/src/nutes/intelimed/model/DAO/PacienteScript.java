package nutes.intelimed.model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PacienteScript extends PacienteDao{


	// Script para fazer drop na tabela
	private static final String[] SCRIPT_DATABASE_DELETE = new String[]{
		"DROP TABLE IF EXISTS paciente;",
		"DROP TABLE IF EXISTS doenca;",
		"DROP TABLE IF EXISTS report_suspeita;"
	};

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table paciente ( _id integer primary key autoincrement, nome text not null,dtnascimento text not null);",
			"create table doenca ( _id integer primary key autoincrement, nome varchar(120));",
			"create table report_suspeita ( _id integer primary key autoincrement, suspeita varchar(120),id_paciente integer, id_doenca integer, Foreign Key (id_paciente) references paciente(_id), Foreign Key (id_doenca) references doenca(_id));",
			"insert into paciente (nome,dtnascimento) values('jamilson','21/11/1989');",
			"insert into doenca (nome) values('asma');",
			"insert into report_suspeita (suspeita,id_paciente,id_doenca) values('falta de ar',1,1);"
			 };

	// Nome do banco
	private static final String NOME_BANCO = "inteliMobile";

	// Controle de versão
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String NOME_TABELA = "paciente";

	// Classe utilitária para abrir, criar, e atualizar o banco de dados
	private SQLiteHelper dbHelper;

	/**
	 * Cria o banco de dados com um script SQL
	 */
	
	public PacienteScript(Context ctx) {
		
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, PacienteScript.NOME_BANCO, PacienteScript.VERSAO_BANCO,
				PacienteScript.SCRIPT_DATABASE_CREATE, PacienteScript.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar também
		db = dbHelper.getWritableDatabase();
		Log.i("jamilson", "passou2");
	}
	/**
	 * Fecha o banco
	 */
	//@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}