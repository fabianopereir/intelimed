

<%@ page import="intermediate.Evidencia" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'evidencia.label', default: 'Evidencia')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${evidenciaInstance}">
            <div class="errors">
                <g:renderErrors bean="${evidenciaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${evidenciaInstance?.id}" />
                <g:hiddenField name="version" value="${evidenciaInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="justificativa"><g:message code="evidencia.justificativa.label" default="Justificativa" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evidenciaInstance, field: 'justificativa', 'errors')}">
                                    <g:textField name="justificativa" value="${evidenciaInstance?.justificativa}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="medico"><g:message code="evidencia.medico.label" default="Medico" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evidenciaInstance, field: 'medico', 'errors')}">
                                    <g:textField name="medico" value="${evidenciaInstance?.medico}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="respostas"><g:message code="evidencia.respostas.label" default="Respostas" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evidenciaInstance, field: 'respostas', 'errors')}">
                                    <g:select name="respostas" from="${intermediate.Resposta.list()}" multiple="yes" optionKey="id" size="5" value="${evidenciaInstance?.respostas*.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="sistema"><g:message code="evidencia.sistema.label" default="Sistema" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evidenciaInstance, field: 'sistema', 'errors')}">
                                    <g:textField name="sistema" value="${evidenciaInstance?.sistema}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
