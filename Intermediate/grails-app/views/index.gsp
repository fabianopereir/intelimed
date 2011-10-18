<html>
    <head>
        <title>InteliMED</title>
        <meta name="layout" content="main" />
        <style type="text/css" media="screen">
        
        /*html, body, #wrap, #pageBody {height: 100%;}
		body > #wrap {height: auto; min-height: 100%;}*/
        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;
			padding-bottom: 40px;
			/*aque*/
        }
        #footnote{
        	position: absolute;
        	bottom: 0px;
        	width: 100%;
			margin-top: -40px;
			height: 40px;
			clear: both;
			border-top-width: 6px;
			border-top-style: solid;
			border-top-color: #ACE149;
			background: -webkit-linear-gradient(45deg, rgba(89,89,89,1) 2%,rgba(102,102,102,1) 14%,rgba(76,76,76,1) 18%,rgba(71,71,71,1) 26%,rgba(44,44,44,1) 34%,rgba(17,17,17,1) 47%,rgba(0,0,0,1) 64%,rgba(43,43,43,1) 76%,rgba(17,17,17,1) 81%,rgba(17,17,17,1) 88%,rgba(28,28,28,1) 91%,rgba(19,19,19,1) 100%);
			color: white;
			text-align: center;
			font-size:1.1em;
        }
        /*aque*/
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            /*list-style-type:circle;*/
            list-style-image:url(images/green_right_arrow.png);
            margin-left:10px;
            margin-bottom:10px;
            font-size:1.1em;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.7em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        <div id="nav">
            <div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">
                	<h1>Menu</h1>
                	<ul>
	                	<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
	                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
	                    </g:each>
                    </ul>
                    
                    <!--  
                    <h1>Application Status</h1>
                    <ul>
                        <li>App version: <g:meta name="app.version"></g:meta></li>
                        <li>Grails version: <g:meta name="app.grails.version"></g:meta></li>
                        <li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
                        <li>JVM version: ${System.getProperty('java.version')}</li>
                        <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                        <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                        <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                        <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
                    </ul>
                    <h1>Installed Plugins</h1>
                    <ul>
                        <g:set var="pluginManager"
                               value="${applicationContext.getBean('pluginManager')}"></g:set>

                        <g:each var="plugin" in="${pluginManager.allPlugins}">
                            <li>${plugin.name} - ${plugin.version}</li>
                        </g:each>

                    </ul>
                    -->
                </div>
                <div class="panelBtm"></div>
            </div>
        </div>
        <div id="pageBody">
            <h1>Bem Vindo ao InteliMED!</h1>
            <p>< Em constru&#231;&#227;o..... ></p>
			
			<!--  
            <div id="controllerList" class="dialog">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                    </g:each>
                </ul>
            </div>
            -->
        </div>
        <div id="footnote">
        	<p> Servidor Intermedi&#225;rio - InteliMED - 2011 </p>
        </div>
    </body>
</html>
