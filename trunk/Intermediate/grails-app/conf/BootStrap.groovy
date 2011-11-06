import intelimed.access.Requestmap
import intelimed.access.Role
import intelimed.access.SecUser
import intelimed.access.SecUserRole
import intermediate.Aresta
import intermediate.Arvore
import intermediate.Evidencia
import intermediate.No
import intermediate.Resposta


class BootStrap {

	def springSecurityService


	def init = { servletContext ->

		String password = springSecurityService.encodePassword('123')

		def userAdmin = new SecUser(username: 'admin', enabled: true, password: password).save()

		def adminRole = new Role(authority: 'ROLE_ADMIN').save()

		SecUserRole.create userAdmin, adminRole, true

		new Requestmap(url: '/js/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

		new Requestmap(url: '/css/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

		new Requestmap(url: '/images/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

		new Requestmap(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

		new Requestmap(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

		new Requestmap(url: '/**', configAttribute: 'ROLE_ADMIN').save()
		
		/*
		//Testes para a arvore de tenis!
		def resposta1 = new Resposta(descricao: 'Sunny').save()
		def resposta2 = new Resposta(descricao: 'Overcast').save()
		def resposta3 = new Resposta(descricao: 'Rain').save()
		def resposta4 = new Resposta(descricao: 'High').save()
		def resposta5 = new Resposta(descricao: 'Normal').save()
		def resposta6 = new Resposta(descricao: 'Strong').save()
		def resposta7 = new Resposta(descricao: 'Weak').save()
		
		def aresta1 = new Aresta(resposta: resposta1).save()
		def aresta2 = new Aresta(resposta: resposta2).save()
		def aresta3 = new Aresta(resposta: resposta3).save()
		def aresta4 = new Aresta(resposta: resposta4).save()
		def aresta5 = new Aresta(resposta: resposta5).save()
		def aresta6 = new Aresta(resposta: resposta6).save()
		def aresta7 = new Aresta(resposta: resposta7).save()
		
		def no1 = new No(descricao: 'Outlook', respostas:[resposta1,resposta2,resposta3]).save()
		def no2 = new No(descricao: 'Humidity', respostas:[resposta4,resposta5], arestasEntrada:[aresta1]).save()
		def no3 = new No(descricao: 'Wind', respostas:[resposta6,resposta7], arestasEntrada:[aresta3]).save()
		def no4 = new No(descricao: 'Yes', arestasEntrada:[aresta2, aresta5, aresta7]).save()
		def no5 = new No(descricao: 'No', arestasEntrada:[aresta4, aresta6]).save()
		
		def arvore = new Arvore(nos:[no1,no2,no3,no4,no5], arestas:[aresta1,aresta2,aresta3,aresta4,aresta5,aresta6,aresta7], respostas:[resposta1,resposta2,resposta3,resposta4,resposta5,resposta6,resposta7]).save()
		def evidencia = new Evidencia(respostas:[resposta3,resposta4]).save()
		*/
		
		
		//Testes para a arvore de asma!
		//Respostas a esquerda da raiz
		def resposta1 = new Resposta(descricao: 'Sim').save()
		
		def resposta2 = new Resposta(descricao: 'Sim').save()
		def resposta3 = new Resposta(descricao: 'Nao').save()
		def resposta4 = new Resposta(descricao: 'Nao sei').save()
		
		def resposta5 = new Resposta(descricao: 'Sim').save()
		def resposta6 = new Resposta(descricao: 'Nao').save()
		
		def resposta7 = new Resposta(descricao: 'Nao').save()
		def resposta8 = new Resposta(descricao: 'Sim, no ultimo mes').save()
		def resposta9 = new Resposta(descricao: 'Sim, no ultimo ano').save()
		
		//Respostas a direita da raiz
		def resposta10 = new Resposta(descricao: 'Nao').save()
		
		def resposta11 = new Resposta(descricao: 'Nenhuma').save()
		def resposta12 = new Resposta(descricao: '1 a 3 crises').save()
		def resposta13 = new Resposta(descricao: '4 a 12 crises').save()
		def resposta14 = new Resposta(descricao: 'Mais de 12 crises').save()
		
		def resposta15 = new Resposta(descricao: 'Sim').save()
		def resposta16 = new Resposta(descricao: 'Nao').save()
		
		def resposta17 = new Resposta(descricao: 'Sim, com sprays ou bombinhas').save()
		def resposta18 = new Resposta(descricao: 'Sim, com nebulizacoes').save()
		def resposta19 = new Resposta(descricao: 'Nao').save()
		def resposta20 = new Resposta(descricao: 'Sim, ambos').save()
		
		def resposta21 = new Resposta(descricao: 'Sim').save()
		def resposta22 = new Resposta(descricao: 'Nao').save()
		def resposta23 = new Resposta(descricao: 'Nao sei').save()
				
		//Arestas com as respostas
		def aresta1 = new Aresta(resposta: resposta1).save()
		def aresta2 = new Aresta(resposta: resposta2).save()
		def aresta3 = new Aresta(resposta: resposta3).save()
		def aresta4 = new Aresta(resposta: resposta4).save()
		def aresta5 = new Aresta(resposta: resposta5).save()
		def aresta6 = new Aresta(resposta: resposta6).save()
		def aresta7 = new Aresta(resposta: resposta7).save()
		def aresta8 = new Aresta(resposta: resposta8).save()
		def aresta9 = new Aresta(resposta: resposta9).save()
		def aresta10 = new Aresta(resposta: resposta10).save()
		def aresta11 = new Aresta(resposta: resposta11).save()
		def aresta12 = new Aresta(resposta: resposta12).save()
		def aresta13 = new Aresta(resposta: resposta13).save()
		def aresta14 = new Aresta(resposta: resposta14).save()
		def aresta15 = new Aresta(resposta: resposta15).save()
		def aresta16 = new Aresta(resposta: resposta16).save()
		def aresta17 = new Aresta(resposta: resposta17).save()
		def aresta18 = new Aresta(resposta: resposta18).save()
		def aresta19 = new Aresta(resposta: resposta19).save()
		def aresta20 = new Aresta(resposta: resposta20).save()
		def aresta21 = new Aresta(resposta: resposta21).save()
		def aresta22 = new Aresta(resposta: resposta22).save()
		def aresta23 = new Aresta(resposta: resposta23).save()
		
		//Nos na sequencia definida na arvore
		def no1 = new No(descricao: 'Seu filho ou sua filha tosse ou tem chiado no peito mesmo quando nao esta resfriado?', respostas:[resposta1, resposta10]).save()
		def no2 = new No(descricao: 'Seu filho ou sua filha tem crises de tosse ou chiado no peito quando tem contato com poeira de casa?', respostas:[resposta2, resposta3, resposta4], arestasEntrada:[aresta1]).save()
		def no3 = new No(descricao: 'Nos ultimos 12 (doze) meses, quantas crises de sibilos (chiado no peito) seu filho(a) teve?', respostas:[resposta11, resposta12, resposta13, resposta14], arestasEntrada:[aresta10]).save()
		def no4 = new No(descricao: 'Seu filho ou sua filha tosse ou tem chiado no peito quando se acorda?', respostas:[resposta5, resposta6], arestasEntrada:[aresta3]).save()
		def no5 = new No(descricao: 'Seu filho(a) tem crises de tosse ou sibilos (chiado no peito) quando faz exercicios fisicos, do tipo correr ou jogar bola?', respostas:[resposta15, resposta16], arestasEntrada:[aresta11]).save()
		def no6 = new No(descricao: 'Seu filho ou sua filha vem apresentando aperto no peito ou dificuldade para respirar?', respostas:[resposta7, resposta8, resposta9], arestasEntrada:[aresta6]).save()
		def no7 = new No(descricao: 'Seu filho(a) melhora da tosse ou chiado quando usa sprays, bombinhas ou nebulizacoes?', respostas:[resposta17, resposta18, resposta19, resposta20], arestasEntrada:[aresta16]).save()
		def no8 = new No(descricao: 'Seu filho(a) tem crises de tosse ou chiado no peito quando tem contato com mofo?', respostas:[resposta21, resposta22, resposta23], arestasEntrada:[aresta17]).save()
		def no9 = new No(descricao: 'Asma', arestasEntrada:[aresta2, aresta4, aresta5, aresta8, aresta9, aresta12, aresta13, aresta14, aresta15, aresta21, aresta23]).save()
		def no10 = new No(descricao: 'Nao asma', arestasEntrada:[aresta7, aresta18, aresta19, aresta20, aresta22]).save()
		
		//Criacao da arvore
		def arvore = new Arvore(nos:[no1, no2, no3, no4, no5, no6, no7, no8, no9, no10], 
			arestas:[aresta1, aresta2, aresta3, aresta4, aresta5, aresta6, aresta7, aresta8, aresta9, aresta10, aresta11, aresta12, aresta13, aresta14, aresta15, aresta16, aresta17, aresta18, aresta19, aresta20, aresta21, aresta22, aresta23], 
			respostas:[resposta1, resposta2, resposta3, resposta4, resposta5, resposta6, resposta7, resposta8, resposta9, resposta10, resposta11, resposta12, resposta13, resposta14, resposta15, resposta16, resposta17, resposta18, resposta19, resposta20, resposta21, resposta22, resposta23]).save()
			
		def evidencia = new Evidencia(respostas:[resposta1, resposta2], justificativa:"").save()
	}

	def destroy = {

	}

}