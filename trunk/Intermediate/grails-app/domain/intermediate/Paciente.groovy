package intermediate

class Paciente {
	
    static constraints = {
		idPaciente(min:0)
		nome(blank:false, maxSize:250)
		nomeDaMae(blank:false, maxSize:250)
		dataDeNascimento()
		idResidencia(min:0)
		idEndereco(min:0)
    }
	
	int idPaciente
	String nome
	String nomeDaMae
	Date dataDeNascimento
	int idResidencia
	int idEndereco
}
