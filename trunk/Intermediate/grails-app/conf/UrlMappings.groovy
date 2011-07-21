class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/"(view:"/index")
		"/rest/usuario/$cpf?"(controller:"usuario", parseRequest:true, action:"rest")
		"500"(view:'/error')
	}
}
