
<%@ page import="intermediate.Paciente" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paciente.label', default: 'Paciente')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'paciente.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'paciente.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="nomeDaMae" title="${message(code: 'paciente.nomeDaMae.label', default: 'Nome Da Mae')}" />
                        
                            <g:sortableColumn property="dataDeNascimento" title="${message(code: 'paciente.dataDeNascimento.label', default: 'Data De Nascimento')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${pacienteInstanceList}" status="i" var="pacienteInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${pacienteInstance.id}">${fieldValue(bean: pacienteInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: pacienteInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: pacienteInstance, field: "nomeDaMae")}</td>
                        
                            <td><g:formatDate date="${pacienteInstance.dataDeNascimento}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${pacienteInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
