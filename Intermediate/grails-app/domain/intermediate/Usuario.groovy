package intermediate

class Usuario {
	
	static constraints = {
		idUsuario(min:0)
		login(blank:false, maxSize:45)
		senha(blank:false, maxSize:250, password:true)
		tipoDeUsuario(inList:["Admin","Medico","Agente de saude"])
		
		//limitar tamanho corretamente
		
		cpf(blank:false, minSize:11, maxSize:11)
		dataDeNascimento()
    }
	
	int idUsuario
	String cpf
	Date dataDeNascimento
	String tipoDeUsuario
	String senha
	String login
}
