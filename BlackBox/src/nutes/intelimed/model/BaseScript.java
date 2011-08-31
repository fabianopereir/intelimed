package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.model.DAO.StructureQuestionnaireDao;
import nutes.intelimed.model.helper.DatabaseHelper;


/**
 * Classe responsável pela criação, povoamento e remoção do banco que armazena a estrutura do questionário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class BaseScript extends StructureQuestionnaireDao{
	
	
	private DatabaseHelper dbHelper;
	
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
		"DROP TABLE IF EXISTS no;",
		"DROP TABLE IF EXISTS resposta;",
		"DROP TABLE IF EXISTS aresta;",
		"DROP TABLE IF EXISTS evidencia;",
		"DROP TABLE IF EXISTS evidencia_respostas;",
		"DROP TABLE IF EXISTS permissao;",
		"DROP TABLE IF EXISTS grupo;",
		"DROP TABLE IF EXISTS usuario;",
		"DROP TABLE IF EXISTS grupo_permissao;"
	};
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"create table no(idno integer primary key autoincrement, descricao_no varchar(255), diagnostico integer);",
		"create table resposta(idresposta integer primary key autoincrement, descricao_resposta varchar(255), fk_idno integer, code_resposta integer, Foreign Key (fk_idno) references no(idno));",
		"create table aresta(idaresta integer primary key autoincrement, fk_idno integer,  fk_idresposta integer, Foreign Key (fk_idno) references no(idno), Foreign Key (fk_idresposta) references resposta(idresposta));",
		"create table evidencia(idevidencia integer primary key autoincrement, sistema varchar(5),  medico varchar(5), justificativa varchar(255));",
		"create table evidencia_respostas(idevidencia_respostas integer primary key autoincrement, fk_idresposta integer, fk_idevidencia integer, Foreign Key (fk_idevidencia) references evidencia(idevidencia), Foreign Key (fk_idresposta) references resposta(idresposta));",
		"create table permissao(_id integer primary key autoincrement, nome varchar(45), descricao varchar(45));",
		"create table grupo (_id integer primary key autoincrement, nome varchar(45), descricao varchar(45));",
		"create table usuario ( _id integer primary key autoincrement, user text not null,password text not null,id_grupo integer, Foreign Key (id_grupo) references grupo(_id));",
		"create table grupo_permissao (id_grupo integer, id_permissao integer, Foreign Key (id_grupo) references grupo(_id), Foreign Key (id_permissao) references permissao(_id));",
		"insert into no(descricao_no, diagnostico) values('OUTLOOK',0);",
		"insert into no(descricao_no, diagnostico) values('HUMIDITY',0);",
		"insert into no(descricao_no, diagnostico) values('WIND',0);",
		"insert into no(descricao_no, diagnostico) values('YES',1);",
		"insert into no(descricao_no, diagnostico) values('NO',1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sunny',1,1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('OverCast',1,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Rain',1,3);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('High',2,1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Normal',2,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Strong',3,1);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Weak',3,2);",
		"insert into aresta(fk_idno, fk_idresposta) values(2,1);",
		"insert into aresta(fk_idno, fk_idresposta) values(4,2);",
		"insert into aresta(fk_idno, fk_idresposta) values(3,3);",		
		"insert into aresta(fk_idno, fk_idresposta) values(5,4);",
		"insert into aresta(fk_idno, fk_idresposta) values(4,5);",
		"insert into aresta(fk_idno, fk_idresposta) values(5,6);",
		"insert into aresta(fk_idno, fk_idresposta) values(4,7);",
		"insert into permissao (nome,descricao) values('deletar','paciente');",
		"insert into grupo (nome,descricao) values('acs','Agentes de saúde');",
		"insert into usuario (user,password,id_grupo) values('jamilson','202cb962ac59075b964b07152d234b70', 1);"
	};
	

	/**
	 * Acessa o banco de dados com um script SQL
	 * @param ctx - contexto que será criado o banco
	 */
	public BaseScript(Context ctx) {
		dbHelper = new DatabaseHelper(ctx, BaseScript.NOME_BANCO, BaseScript.VERSAO_BANCO,
				BaseScript.getScriptDatabaseCreate(), BaseScript.getScriptDatabaseDelete());

		db = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Busca script de criação da base de dados
	 * @return script da base de dados
	 */
	public static String[] getScriptDatabaseCreate() {
		return SCRIPT_DATABASE_CREATE;
	}
	
	/**
	 * Busca script para deletar a base de dados
	 * @return script da base de dados
	 */
	public static String[] getScriptDatabaseDelete() {
		return SCRIPT_DATABASE_DELETE;
	}
	
	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}	
}