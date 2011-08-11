package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.model.DAO.StructureQuestionnaireDao;
import nutes.intelimed.model.helper.SQLiteHelper;


/**
 * Classe respons�vel pela cria��o, povoamento e remo��o do banco que armazena a estrutura do question�rio
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class StructureQuestionnaireScript extends StructureQuestionnaireDao{
	
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
		"DROP TABLE IF EXISTS no;",
		"DROP TABLE IF EXISTS resposta;",
		"DROP TABLE IF EXISTS aresta;",
		"DROP TABLE IF EXISTS evidencia;",
		"DROP TABLE IF EXISTS evidencia_respostas;"
	};
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"create table no(idno integer primary key autoincrement, descricao_no varchar(255), diagnostico integer);",
		"create table resposta(idresposta integer primary key autoincrement, descricao_resposta varchar(255), fk_idno integer, code_resposta integer, Foreign Key (fk_idno) references no(idno));",
		"create table aresta(idaresta integer primary key autoincrement, fk_idno integer,  fk_idresposta integer, Foreign Key (fk_idno) references no(idno), Foreign Key (fk_idresposta) references resposta(idresposta));",
		"create table evidencia(idevidencia integer primary key autoincrement, sistema varchar(5),  medico varchar(5), justificativa varchar(255));",
		"create table evidencia_respostas(idevidencia_respostas integer primary key autoincrement, fk_idresposta integer, fk_idno integer,  fk_idevidencia integer, Foreign Key (fk_idno) references no(idno), Foreign Key (fk_idevidencia) references evidencia(idevidencia), Foreign Key (fk_idresposta) references resposta(idresposta));",
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
		"insert into aresta(fk_idno, fk_idresposta) values(4,7);"
	};
	public static final String NOME_BANCO = "caixapreta";
	public static final int VERSAO_BANCO = 6;
	
	private SQLiteHelper dbHelper;

	/**
	 * Cria o banco de dados com um script SQL
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @param ctx - contexto que ser� criado o banco
	 */
	public StructureQuestionnaireScript(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, StructureQuestionnaireScript.NOME_BANCO, StructureQuestionnaireScript.VERSAO_BANCO,
				StructureQuestionnaireScript.getScriptDatabaseCreate(), StructureQuestionnaireScript.getScriptDatabaseDelete());

		db = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Busca script de cria��o da base de dados
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
	 * @return script da base de dados
	 */
	public static String[] getScriptDatabaseCreate() {
		return SCRIPT_DATABASE_CREATE;
	}
	
	/**
	 * Busca script para deletar a base de dados
	 * @author Jamilson Batista (jamilsonbatista@gmail.com)
	 * @author Dyego Carlos (dyego12345@gmail.com)
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