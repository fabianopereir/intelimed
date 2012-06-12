
<%@ page import="intermediate.Evidencia" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'evidencia.label', default: 'Evidencia')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'evidencia.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="justificativa" title="${message(code: 'evidencia.justificativa.label', default: 'Justificativa')}" />
                        
                            <g:sortableColumn property="medico" title="${message(code: 'evidencia.medico.label', default: 'Medico')}" />
                        
                            <g:sortableColumn property="sistema" title="${message(code: 'evidencia.sistema.label', default: 'Sistema')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${evidenciaInstanceList}" status="i" var="evidenciaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${evidenciaInstance.id}">${fieldValue(bean: evidenciaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: evidenciaInstance, field: "justificativa")}</td>
                        
                            <td>${fieldValue(bean: evidenciaInstance, field: "medico")}</td>
                        
                            <td>${fieldValue(bean: evidenciaInstance, field: "sistema")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${evidenciaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
