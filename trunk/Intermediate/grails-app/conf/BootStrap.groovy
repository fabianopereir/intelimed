import intelimed.access.Requestmap
import intelimed.access.Role
import intelimed.access.SecUser
import intelimed.access.SecUserRole


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

	}

	def destroy = {

	}

}