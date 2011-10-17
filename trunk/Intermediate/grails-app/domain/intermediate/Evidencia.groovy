package intermediate

class Evidencia {
	
	//A evidencia engloba todas as respostas obtidas de um questionario
	
	static hasMany = [respostas:Resposta]
	
    static constraints = {
    }
}
