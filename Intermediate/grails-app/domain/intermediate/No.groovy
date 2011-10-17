package intermediate

class No {
	
	//O No representa uma pergunta ou descricao que possui, ou não, respostas e arestas associadas
	//As respostas sao os possiveis caminhos que levam a outros Nos
	//As arestas de entrada representam os caminhos que levam ate o No em questao
	
	String descricao
	static hasMany = [respostas:Resposta, arestasEntrada:Aresta]
    
	String toString(){
		descricao
	}
	
	static constraints = {
    }
}
