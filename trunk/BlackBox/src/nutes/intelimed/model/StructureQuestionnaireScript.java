package nutes.intelimed.model;

import android.content.Context;
import android.util.Log;
import nutes.intelimed.model.SQLiteHelper;
import nutes.intelimed.model.DAO.StructureQuestionnaireDao;

public class StructureQuestionnaireScript extends StructureQuestionnaireDao{
	
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
		"DROP TABLE IF EXISTS padra_validacao;",
		"DROP TABLE IF EXISTS metrica;",
		"DROP TABLE IF EXISTS validacao_metrica;",
		"DROP TABLE IF EXISTS questao;",
		"DROP TABLE IF EXISTS diagnostico;",
		"DROP TABLE IF EXISTS resposta;",
		"DROP TABLE IF EXISTS estrutura_questionario;"
	};
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table padra_validacao(idpadrao_validacao integer primary key autoincrement, padrao int, descricao varchar(255));",
			"create table metrica (idmetrica integer primary key autoincrement, descricao varchar(20), unidade varchar(45));",
			"create table validacao_metrica (idvalidacao_metrica integer primary key autoincrement,,fk_idmetrica integer, fk_idpadrao_validacao integer, Foreign Key (fk_idpadrao_validacao) references padrao_validacao(idpadrao_validacao),Foreign Key (fk_idmetrica) references metrica(idmetrica));",
			"create table questao (idquestao integer, pergunta text, fk_idmetrica integer, Foreign Key (fk_idmetrica) references metrica(idmetrica);",
			"create table diagnostico (iddiagnostico integer primary key autoincrement, date varchar(10), resultado_sis boolean, resultado_medico boolean, explicacao varchar (255);",
			"create table estrutura_questionario (idresposta integer, ordem integer, fk_iddiagnostico integer,fk_idquestao integer, Foreign Key (fk_iddiagnostico) references diagnostico(iddiagnostico), Foreign Key (fk_idquestao) references questao(idquestao));",
			"create table resposta (idresposta integer, valor varchar(255), fk_idestrutura_questionario integer, Foreign Key (fk_idestrutura_questionario) references estrutura_questionario(idestrutura_questionario);",
			"insert into padra_validacao (padrao,descricao) values(1,'não');",
			"insert into padra_validacao (padrao,descricao) values(2,'sim');",
			"insert into metrica (descricao,unidade) values('radio group','pergunta 1');",
			"insert into validacao_metrica (fk_idmetrica,fk_idpadrao_validacao) values(1,1);",
			"insert into validacao_metrica (fk_idmetrica,fk_idpadrao_validacao) values(1,2);",
			"insert into questao (pergunta,fk_idmetrica) values('Qual seu nome?',1);",
			"insert into diagnostico (date,resultado_sis,resultado_medico,explicacao) values('21/05/2011',true,,true,'');",
			"insert into estrutura_questionario (ordem,fk_iddiagnostico,fk_idquestao) values(1,1,1);"
			 };
	private static final String NOME_BANCO = "caixapreta";
	private static final int VERSAO_BANCO = 1;
	
	//public static final String NOME_TABELA = "questao";
	
	private SQLiteHelper dbHelper;

	public StructureQuestionnaireScript(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, StructureQuestionnaireScript.NOME_BANCO, StructureQuestionnaireScript.VERSAO_BANCO,
				StructureQuestionnaireScript.SCRIPT_DATABASE_CREATE, StructureQuestionnaireScript.SCRIPT_DATABASE_DELETE);

		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void fechar() {
		super.fechar();
		Log.i("jamilson", "Mandou fechar o banco");
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
