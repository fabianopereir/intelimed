package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.helper.SQLiteHelper;
import nutes.intelimed.model.DAO.StructureQuestionnaireDao;


/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description script responsável pela criação, povoamento e remoção do banco que armazena a estrutura do questionário
 */
public class StructureQuestionnaireScript extends StructureQuestionnaireDao{
	
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
		"DROP TABLE IF EXISTS no;",
		"DROP TABLE IF EXISTS resposta;",
		"DROP TABLE IF EXISTS aresta;"
	};
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"create table no(idno integer primary key autoincrement, descricao_no varchar(255), diagnostico integer);",
		"create table resposta(idresposta integer primary key autoincrement, descricao_resposta varchar(255), fk_idno integer, Foreign Key (fk_idno) references no(idno));",
		"create table aresta(idaresta integer primary key autoincrement, fk_idno integer,  fk_idresposta integer, Foreign Key (fk_idno) references no(idno), Foreign Key (fk_idresposta) references resposta(idresposta));",
		"insert into no(descricao_no, diagnostico) values('OUTLOOK',0);",
		"insert into no(descricao_no, diagnostico) values('HUMIDITY',0);",
		"insert into no(descricao_no, diagnostico) values('WIND',0);",
		"insert into no(descricao_no, diagnostico) values('YES',1);",
		"insert into no(descricao_no, diagnostico) values('NO',1);",
		"insert into resposta(descricao_resposta, fk_idno) values('Sunny',1);",
		"insert into resposta(descricao_resposta, fk_idno) values('OverCast',1);",
		"insert into resposta(descricao_resposta, fk_idno) values('Rain',1);",
		"insert into resposta(descricao_resposta, fk_idno) values('High',2);",
		"insert into resposta(descricao_resposta, fk_idno) values('Normal',2);",
		"insert into resposta(descricao_resposta, fk_idno) values('Strong',3);",
		"insert into resposta(descricao_resposta, fk_idno) values('Weak',3);",
		"insert into aresta(fk_idno, fk_idresposta) values(2,1);",
		"insert into aresta(fk_idno, fk_idresposta) values(4,2);",
		"insert into aresta(fk_idno, fk_idresposta) values(3,3);",		
		"insert into aresta(fk_idno, fk_idresposta) values(5,4);",
		"insert into aresta(fk_idno, fk_idresposta) values(5,5);",
		"insert into aresta(fk_idno, fk_idresposta) values(4,6);",
	};
	private static final String NOME_BANCO = "caixapreta";
	private static final int VERSAO_BANCO = 3;
	
	private SQLiteHelper dbHelper;

	public StructureQuestionnaireScript(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, StructureQuestionnaireScript.NOME_BANCO, StructureQuestionnaireScript.VERSAO_BANCO,
				StructureQuestionnaireScript.SCRIPT_DATABASE_CREATE, StructureQuestionnaireScript.SCRIPT_DATABASE_DELETE);

		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}