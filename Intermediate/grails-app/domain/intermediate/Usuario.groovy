package intermediate

import java.util.Date;

class Usuario {
	
static constraints = {	
		nome(blank:false)
		login(blank:false, maxSize:45, unique:true)
		senha(blank:false, maxSize:250, password:true)
		tipoDeUsuario(inList:["Admin","Medico","Agente de saude"])
		cpf(blank:false, minSize:11, maxSize:11, matches:"[0-9]+", unique:true)
		//dataDeNascimento()
    }

	String nome
	String cpf
	//Date dataDeNascimento
	String tipoDeUsuario
	String senha
	String login
}
