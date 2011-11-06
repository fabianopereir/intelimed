package intermediate

import grails.converters.*

class EvidenciaController {

	def xmlList = {
		render Evidencia.list() as XML
	}

	def xmlShow = {
		render Evidencia.get(params.id) as XML
	}

	def debugAccept = {
		def clientRequest = request.getHeader("accept")
		def serverResponse = request.format
		render "Client: ${clientRequest}\nServer: ${serverResponse}\n"
	}
	
	def rest = {
		
		switch(request.method){
			
			case "POST":
				
				//Why u no work XML?! Apenas JSON!
				def evidencia = new Evidencia()
				evidencia.justificativa = params.evidencia.justificativa
				
				Resposta.getAll(params.evidencia.respostas.id).each {
					evidencia.addToRespostas(it)
				}
				
				if(evidencia.save()){
					response.status = 201 // Created
					render evidencia as JSON
				}
				else{
					response.status = 500 //Internal Server Error
					render "Could not create new Evidencia due to errors:\n ${evidencia.errors}"
				}
				break
			case "GET":
				if(params.id){
					render Evidencia.findById(params.id) as JSON
				}
				else{
					render Evidencia.list() as JSON
				}
				break
			case "PUT":
				//Problema da remanescencia de respostas passadas
				//Limpar antes de atualizar e apenas JSON!
			
				def evidencia = Evidencia.findById(params.evidencia.id)
				evidencia.justificativa = params.evidencia.justificativa
				Resposta.getAll(params.evidencia.respostas.id).each {
					evidencia.addToRespostas(it)
				}
				
				evidencia.properties = params.evidencia
				if(evidencia.save()){
					response.status = 200 // OK
					render evidencia as JSON
				}
				else{
					response.status = 500 //Internal Server Error
					render "Could not create new Evidencia due to errors:\n ${evidencia.errors}"
				}
				break
			case "DELETE":
				if(params.id){
					def evidencia = Evidencia.findById(params.id)
					if(evidencia){
						evidencia.delete()
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
				  Example: /rest/evidencia/id"""
				}
				break
		}
	}
	
	def list = {
		if(!params.max) params.max = 10
		def list = Evidencia.list(params)
		withFormat{
			html{
				[evidenciaList:list, evidenciaInstanceList: Evidencia.list(params), evidenciaInstanceTotal: Evidencia.count()]
			}
			xml{ render list as XML }
			json{ render list as JSON }
		}
	}
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
	
	/*
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [evidenciaInstanceList: Evidencia.list(params), evidenciaInstanceTotal: Evidencia.count()]
    }
    */

    def create = {
        def evidenciaInstance = new Evidencia()
        evidenciaInstance.properties = params
        return [evidenciaInstance: evidenciaInstance]
    }

    def save = {
        def evidenciaInstance = new Evidencia(params)
        if (evidenciaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), evidenciaInstance.id])}"
            redirect(action: "show", id: evidenciaInstance.id)
        }
        else {
            render(view: "create", model: [evidenciaInstance: evidenciaInstance])
        }
    }

    def show = {
        def evidenciaInstance = Evidencia.get(params.id)
        if (!evidenciaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), params.id])}"
            redirect(action: "list")
        }
        else {
            [evidenciaInstance: evidenciaInstance]
        }
    }

    def edit = {
        def evidenciaInstance = Evidencia.get(params.id)
        if (!evidenciaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [evidenciaInstance: evidenciaInstance]
        }
    }

    def update = {
        def evidenciaInstance = Evidencia.get(params.id)
        if (evidenciaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (evidenciaInstance.version > version) {
                    
                    evidenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'evidencia.label', default: 'Evidencia')] as Object[], "Another user has updated this Evidencia while you were editing")
                    render(view: "edit", model: [evidenciaInstance: evidenciaInstance])
                    return
                }
            }
            evidenciaInstance.properties = params
            if (!evidenciaInstance.hasErrors() && evidenciaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), evidenciaInstance.id])}"
                redirect(action: "show", id: evidenciaInstance.id)
            }
            else {
                render(view: "edit", model: [evidenciaInstance: evidenciaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def evidenciaInstance = Evidencia.get(params.id)
        if (evidenciaInstance) {
            try {
                evidenciaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'evidencia.label', default: 'Evidencia'), params.id])}"
            redirect(action: "list")
        }
    }
}
