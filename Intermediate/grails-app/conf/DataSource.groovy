dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
	//driverClassName = "org.hsqldb.jdbcDriver"
	//username = "sa"
    username = "root"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			driverClassName = "org.hsqldb.jdbcDriver"
			url = "jdbc:hsqldb:mem:devDb"
			username = "sa"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            //url = "jdbc:hsqldb:mem:testDb"
			url = "jdbc:mysql://localhost:3306/intermediate_test"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "intelimed"
			password = "intermediatedb"
//			driverClassName = "org.hsqldb.jdbcDriver"
//			url = "jdbc:hsqldb:file:prodDb"
//			username = "sa"
//			password = ""
			url = "jdbc:mysql://localhost:3306/intelimed"
        }
    }
}
