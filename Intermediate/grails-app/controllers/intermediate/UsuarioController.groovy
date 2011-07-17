package intermediate

import grails.converters.*

class UsuarioController {

	def xmlList = {
		render Usuario.list() as XML
	}

	def xmlShow = {
		render Usuario.get(params.id) as XML
	}

	def debugAccept = {
		def clientRequest = request.getHeader("accept")
		def serverResponse = request.format
		render "Client: ${clientRequest}\nServer: ${serverResponse}\n"
	}

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def rest = {
		switch(request.method){
			case "POST":
				render "Create\n"
				break
			case "GET":
				if(params.cpf){
					render Usuario.findByCpf(params.cpf) as XML
				}
				else{
					render Usuario.list() as XML
				}
				break
			case "PUT":
				render "Update\n"
				break
			case "DELETE":
				render "Delete\n"
				break
		}
	}

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		if(!params.max) params.max = 10
		def list = Usuario.list(params)
		withFormat{
			html{
				[usuarioList:list, usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
			}
			xml{ render list as XML }
			json{ render list as JSON }
		}
	}

	/*def list = {
	 params.max = Math.min(params.max ? params.int('max') : 10, 100)
	 [usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
	 }*/

	def create = {
		def usuarioInstance = new Usuario()
		usuarioInstance.properties = params
		return [usuarioInstance: usuarioInstance]
	}

	def save = {
		def usuarioInstance = new Usuario(params)
		if (usuarioInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])}"
			redirect(action: "show", id: usuarioInstance.id)
		}
		else {
			render(view: "create", model: [usuarioInstance: usuarioInstance])
		}
	}

	def show = {
		def usuarioInstance = Usuario.get(params.id)
		if (!usuarioInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])}"
			redirect(action: "list")
		}
		else {
			[usuarioInstance: usuarioInstance]
		}
	}

	def edit = {
		def usuarioInstance = Usuario.get(params.id)
		if (!usuarioInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [usuarioInstance: usuarioInstance]
		}
	}

	def update = {
		def usuarioInstance = Usuario.get(params.id)
		if (usuarioInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (usuarioInstance.version > version) {

					usuarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'usuario.label', default: 'Usuario')]
					as Object[], "Another user has updated this Usuario while you were editing")
					render(view: "edit", model: [usuarioInstance: usuarioInstance])
					return
				}
			}
			usuarioInstance.properties = params
			if (!usuarioInstance.hasErrors() && usuarioInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])}"
				redirect(action: "show", id: usuarioInstance.id)
			}
			else {
				render(view: "edit", model: [usuarioInstance: usuarioInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def usuarioInstance = Usuario.get(params.id)
		if (usuarioInstance) {
			try {
				usuarioInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])}"
			redirect(action: "list")
		}
	}
}