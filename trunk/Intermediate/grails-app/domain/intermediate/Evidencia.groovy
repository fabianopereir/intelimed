package intermediate

class Evidencia {
	
	//A evidencia engloba todas as respostas obtidas de um questionario
	//Justificativa faz referencia a opiniao do medico em relacao a do sistema
	
	static hasMany = [respostas:Resposta]
	String justificativa
	
	String toString(){
		justificativa
	}
	
    static constraints = {
    }
}
