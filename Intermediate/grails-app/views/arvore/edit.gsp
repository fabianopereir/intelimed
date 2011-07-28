

<%@ page import="intermediate.Arvore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'arvore.label', default: 'Arvore')}" />
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
            <g:hasErrors bean="${arvoreInstance}">
            <div class="errors">
                <g:renderErrors bean="${arvoreInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${arvoreInstance?.id}" />
                <g:hiddenField name="version" value="${arvoreInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="arestas"><g:message code="arvore.arestas.label" default="Arestas" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: arvoreInstance, field: 'arestas', 'errors')}">
                                    <g:select name="arestas" from="${intermediate.Aresta.list()}" multiple="yes" optionKey="id" size="5" value="${arvoreInstance?.arestas*.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nos"><g:message code="arvore.nos.label" default="Nos" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: arvoreInstance, field: 'nos', 'errors')}">
                                    <g:select name="nos" from="${intermediate.No.list()}" multiple="yes" optionKey="id" size="5" value="${arvoreInstance?.nos*.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="respostas"><g:message code="arvore.respostas.label" default="Respostas" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: arvoreInstance, field: 'respostas', 'errors')}">
                                    <g:select name="respostas" from="${intermediate.Resposta.list()}" multiple="yes" optionKey="id" size="5" value="${arvoreInstance?.respostas*.id}" />
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
