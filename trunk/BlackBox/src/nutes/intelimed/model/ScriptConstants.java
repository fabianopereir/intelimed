package nutes.intelimed.model;

/**
 * Classe responsável pela criação, povoamento e remoção do banco que armazena a estrutura do questionário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 */
public class ScriptConstants extends GenericDao{
	
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
		"create table no(idno integer primary key, descricao_no varchar(255), diagnostico integer);",
		"create table resposta(idresposta integer primary key, descricao_resposta varchar(255), fk_idno integer, code_resposta integer, Foreign Key (fk_idno) references no(idno));",
		"create table aresta(idaresta integer primary key, fk_idno integer,  fk_idresposta integer, Foreign Key (fk_idno) references no(idno), Foreign Key (fk_idresposta) references resposta(idresposta));",
		"create table evidencia(idevidencia integer primary key autoincrement, sistema varchar(5),  medico varchar(5), justificativa varchar(255));",
		"create table evidencia_respostas(idevidencia_respostas integer primary key autoincrement, fk_idresposta integer, fk_idevidencia integer, Foreign Key (fk_idevidencia) references evidencia(idevidencia), Foreign Key (fk_idresposta) references resposta(idresposta));",
		"create table permissao(_id integer primary key autoincrement, nome varchar(45), descricao varchar(45));",
		"create table grupo (_id integer primary key autoincrement, nome varchar(45), descricao varchar(45));",
		"create table usuario ( _id integer primary key autoincrement, user text not null,password text not null,id_grupo integer, Foreign Key (id_grupo) references grupo(_id));",
		"create table grupo_permissao (id_grupo integer, id_permissao integer, Foreign Key (id_grupo) references grupo(_id), Foreign Key (id_permissao) references permissao(_id));",
		
		"insert into no(descricao_no, diagnostico) values('1. Seu filho ou sua filha  tosse ou tem chiado no peito mesmo quando não está resfriado?',0);",
		"insert into no(descricao_no, diagnostico) values('2. Seu filho ou sua filha  tem crises de tosse ou chiado  no peito  quando tem contato com poeira de casa?',0);",
		"insert into no(descricao_no, diagnostico) values('3. Nos últimos 12 (doze) meses, quantas crises de sibilos (chiado no peito) seu filho ou sua filha  teve?',0);",
		"insert into no(descricao_no, diagnostico) values('4. Seu filho ou sua filha  tosse ou tem chiado no peito quando se acorda?',0);",
		"insert into no(descricao_no, diagnostico) values('5. Seu filho ou sua filha  tem crises de tosse ou sibilos (chiado no peito) quando faz exercícios físicos, do tipo correr ou jogar bola?',0);",
		"insert into no(descricao_no, diagnostico) values('6. Seu filho ou sua filha vem apresentando aperto no peito ou dificuldade para respirar?',0);",
		"insert into no(descricao_no, diagnostico) values('7. Seu filho ou sua filha  melhora da tosse ou chiado quando usa “sprays” ou bombinhas ou nebulizações?',0);",
		"insert into no(descricao_no, diagnostico) values('8. Seu filho ou sua filha  tem crises de tosse ou chiado no peito quando tem contato com mofo?',0);",
		"insert into no(descricao_no, diagnostico) values('Asma',1);",
		"insert into no(descricao_no, diagnostico) values('Não  Asma',1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sim',1,1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Não',1,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sim',2,1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Não',2,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Não sei',2,3);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Nenhuma',3,1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('1 a 3 crises',3,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('4 a 12 crises',3,3);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Mais de 12 crises',3,4);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sim',4,1);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Não',4,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sim',5,1);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Não',5,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Não',6,1);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Sim, no ultimo mês',6,2);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sim, no ultimo ano',6,3);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Sim, com sprays ou bombinhas',7,1);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sim, com nebulizações',7,2);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Não',7,3);",
		"insert into resposta(descricao_resposta, fk_idno, code_resposta) values('Sim, ambos',7,4);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Sim',8,1);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Não',8,2);",
		"insert into resposta(descricao_resposta, fk_idno,code_resposta) values('Não sei',8,3);",
		//2,3,9,4,9,5,9,9,9,9,6,9,7,10,9,9,8,10,10,10,9,10,9

		"insert into aresta(fk_idno, fk_idresposta) values(2,1);",
		"insert into aresta(fk_idno, fk_idresposta) values(3,2);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,3);",		
		"insert into aresta(fk_idno, fk_idresposta) values(4,4);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,5);",
		"insert into aresta(fk_idno, fk_idresposta) values(5,6);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,7);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,8);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,9);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,10);",
		"insert into aresta(fk_idno, fk_idresposta) values(6,11);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,12);",
		"insert into aresta(fk_idno, fk_idresposta) values(7,13);",
		"insert into aresta(fk_idno, fk_idresposta) values(10,14);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,15);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,16);",
		"insert into aresta(fk_idno, fk_idresposta) values(8,17);",
		"insert into aresta(fk_idno, fk_idresposta) values(10,18);",
		"insert into aresta(fk_idno, fk_idresposta) values(10,19);",
		"insert into aresta(fk_idno, fk_idresposta) values(10,20);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,21);",
		"insert into aresta(fk_idno, fk_idresposta) values(10,22);",
		"insert into aresta(fk_idno, fk_idresposta) values(9,23);",
		
		"insert into permissao (nome,descricao) values('deletar','paciente');",
		"insert into grupo (nome,descricao) values('acs','Agentes de saúde');",
		"insert into usuario (user,password,id_grupo) values('nutes','202cb962ac59075b964b07152d234b70', 1);"
	};
	
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
	
}