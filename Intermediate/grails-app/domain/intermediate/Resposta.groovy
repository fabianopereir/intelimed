package intermediate

class Resposta {
	
	//Representa a resposta de uma pergunta (No)
	
	String descricao
	static belongsTo = No
	
	String toString(){
		descricao
	}
	
    static constraints = {
    }
}