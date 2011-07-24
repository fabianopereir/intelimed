package intermediate

class No {
	
	String descricao
	static hasMany = [respostas:Resposta, arestasEntrada:Aresta]
    
	static constraints = {
    }
}
