package intermediate

class Paciente {
	
    static constraints = {
		nome(blank:false, maxSize:250)
		nomeDaMae(blank:false, maxSize:250)
		dataDeNascimento()
    }
	
	String nome
	String nomeDaMae
	Date dataDeNascimento
}
