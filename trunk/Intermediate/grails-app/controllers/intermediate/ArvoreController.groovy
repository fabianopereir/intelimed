package intermediate

import grails.converters.*

class ArvoreController {

	def xmlList = {
		render Arvore.list() as XML
	}

	def xmlShow = {
		render Arvore.get(params.id) as XML
	}

	def debugAccept = {
		def clientRequest = request.getHeader("accept")
		def serverResponse = request.format
		render "Client: ${clientRequest}\nServer: ${serverResponse}\n"
	}
	
	def rest = {
		switch(request.method){
			case "POST":
				def arvore = new Arvore(params.arvore)
				if(arvore.save()){
					response.status = 201 // Created
					render arvore as XML
				}
				else{
					response.status = 500 //Internal Server Error
					render "Could not create new Arvore due to errors:\n ${arvore.errors}"
				}
				break
			case "GET":
				if(params.id){
					def marinha = Arvore.findById(params.id)
					println marinha.nos as JSON 					
					
					render Arvore.findById(params.id) as XML
				}
				else{
					render Arvore.list() as XML
				}
				break
			case "PUT":
				def arvore = Arvore.findById(params.arvore.id)
				arvore.properties = params.arvore
				if(arvore.save()){
					response.status = 200 // OK
					render arvore as XML
				}
				else{
					response.status = 500 //Internal Server Error
					render "Could not create new Arvore due to errors:\n ${arvore.errors}"
				}
				break
			case "DELETE":
				if(params.id){
					def arvore = Arvore.findById(params.id)
					if(arvore){
						arvore.delete()
						render "Successfully Deleted."
					}
					else{
						response.status = 404 //Not Found
						render "${params.id} not found."
					}
				}
				else{
					response.status = 400 //Bad Request
					render """DELETE request must include the ID
				  Example: /rest/arvore/id"""
				}
				break
		}
	}


	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		if(!params.max) params.max = 10
		def list = Arvore.list(params)
		withFormat{
			html{
				[arvoreList:list, arvoreInstanceList: Arvore.list(params), arvoreInstanceTotal: Arvore.count()]
			}
			xml{ render list as XML }
			json{ render list as JSON }
		}
	}
	
	/*
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[arvoreInstanceList: Arvore.list(params), arvoreInstanceTotal: Arvore.count()]
	}
	*/

	def create = {
		def arvoreInstance = new Arvore()
		arvoreInstance.properties = params
		return [arvoreInstance: arvoreInstance]
	}

	def save = {
		def arvoreInstance = new Arvore(params)
		if (arvoreInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'arvore.label', default: 'Arvore'), arvoreInstance.id])}"
			redirect(action: "show", id: arvoreInstance.id)
		}
		else {
			render(view: "create", model: [arvoreInstance: arvoreInstance])
		}
	}

	def show = {
		def arvoreInstance = Arvore.get(params.id)
		if (!arvoreInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'arvore.label', default: 'Arvore'), params.id])}"
			redirect(action: "list")
		}
		else {
			[arvoreInstance: arvoreInstance]
		}
	}

	def edit = {
		def arvoreInstance = Arvore.get(params.id)
		if (!arvoreInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'arvore.label', default: 'Arvore'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [arvoreInstance: arvoreInstance]
		}
	}

	def update = {
		def arvoreInstance = Arvore.get(params.id)
		if (arvoreInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (arvoreInstance.version > version) {

					arvoreInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'arvore.label', default: 'Arvore')]
					as Object[], "Another user has updated this Arvore while you were editing")
					render(view: "edit", model: [arvoreInstance: arvoreInstance])
					return
				}
			}
			arvoreInstance.properties = params
			if (!arvoreInstance.hasErrors() && arvoreInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'arvore.label', default: 'Arvore'), arvoreInstance.id])}"
				redirect(action: "show", id: arvoreInstance.id)
			}
			else {
				render(view: "edit", model: [arvoreInstance: arvoreInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'arvore.label', default: 'Arvore'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def arvoreInstance = Arvore.get(params.id)
		if (arvoreInstance) {
			try {
				arvoreInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'arvore.label', default: 'Arvore'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'arvore.label', default: 'Arvore'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'arvore.label', default: 'Arvore'), params.id])}"
			redirect(action: "list")
		}
	}
}