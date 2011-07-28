package intermediate

class No {
	
	String descricao
	static hasMany = [respostas:Resposta, arestasEntrada:Aresta]
    
	String toString(){
		descricao
	}
	
	static constraints = {
    }
}
