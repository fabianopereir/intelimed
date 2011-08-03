package nutes.intelimed.model;

import android.content.Context;
import nutes.intelimed.helper.SQLiteHelper;
import nutes.intelimed.model.DAO.StructureQuestionnaireDao;


/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description script responsável pela criação, povoamento e remoção do banco
 */
public class StructureQuestionnaireScript extends StructureQuestionnaireDao{
	
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
		"DROP TABLE IF EXISTS no;",
		"DROP TABLE IF EXISTS resposta;",
		"DROP TABLE IF EXISTS aresta;"
	};
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"create table no(idno integer primary key autoincrement, descricao_no varchar(255));",
		"create table resposta(idresposta integer primary key autoincrement, descricao_resposta varchar(255), fk_idno integer, Foreign Key (fk_idno) references no(idno));",
		"create table aresta(idaresta integer primary key autoincrement, fk_idno integer,  fk_idresposta integer, Foreign Key (fk_idno) references no(idno), Foreign Key (fk_idresposta) references resposta(idresposta));",
		"insert into no(descricao_no) values('OUTLOOK');",
		"insert into no(descricao_no) values('HUMIDITY');",
		"insert into no(descricao_no) values('WIND');",
		"insert into no(descricao_no) values('YES');",
		"insert into no(descricao_no) values('NO');",
		"insert into resposta(descricao_resposta, fk_idno) values('Sunny',1);",
		"insert into resposta(descricao_resposta, fk_idno) values('OverCast',1);",
		"insert into resposta(descricao_resposta, fk_idno) values('Rain',1);",
		"insert into aresta(fk_idno, fk_idresposta) values(3,1);",
		"insert into aresta(fk_idno, fk_idresposta) values(2,2);"
	};
	private static final String NOME_BANCO = "caixapreta";
	private static final int VERSAO_BANCO = 2;
	
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