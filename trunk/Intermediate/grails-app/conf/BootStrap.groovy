import intelimed.access.Requestmap
import intelimed.access.Role
import intelimed.access.SecUser
import intelimed.access.SecUserRole
import intermediate.Aresta
import intermediate.Arvore
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
		
		//Testes para a arvore!
		def resposta1 = new Resposta(resposta: 'Sunny').save()
		def resposta2 = new Resposta(resposta: 'Overcast').save()
		def resposta3 = new Resposta(resposta: 'Rain').save()
		def resposta4 = new Resposta(resposta: 'High').save()
		def resposta5 = new Resposta(resposta: 'Normal').save()
		def resposta6 = new Resposta(resposta: 'Strong').save()
		def resposta7 = new Resposta(resposta: 'Weak').save()
		
		def aresta1 = new Aresta(resposta: resposta1).save()
		def aresta2 = new Aresta(resposta: resposta2).save()
		def aresta3 = new Aresta(resposta: resposta3).save()
		def aresta4 = new Aresta(resposta: resposta4).save()
		def aresta5 = new Aresta(resposta: resposta5).save()
		def aresta6 = new Aresta(resposta: resposta6).save()
		def aresta7 = new Aresta(resposta: resposta7).save()
		
		def no1 = new No(descricao: 'Outlook', respostas:[resposta1,resposta2,resposta3]).save()
		def no2 = new No(descricao: 'Humidity', repostas:[resposta4,resposta5], arestasEntrada:[aresta1]).save()
		def no3 = new No(descricao: 'Wind', respostas:[resposta6,resposta7], arestasEntrada:[aresta3]).save()
		def no4 = new No(descricao: 'Yes', arestasEntrada:[aresta2, aresta5, aresta7]).save()
		def no5 = new No(descricao: 'No', arestasEntrada:[aresta4, aresta6]).save()
		
		def arvore = new Arvore(nos:[no1,no2,no3,no4,no5], arestas:[aresta1,aresta2,aresta3,aresta4,aresta5,aresta6,aresta7], respostas:[resposta1,resposta2,resposta3,resposta4,resposta5,resposta6,resposta7]).save()
	}

	def destroy = {

	}

}