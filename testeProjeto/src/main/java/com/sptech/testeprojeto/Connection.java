
package com.sptech.testeprojeto;

import org.apache.commons.dbcp2.BasicDataSource;
public class Connection {
    private BasicDataSource dataSource;

    public Connection() {
        
        dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://projeto-sac.database.windows.net;database=bd-projeto-sac;encrypt=true;trustServerCertificate=false;hostNameInCertificate=projeto-sac.database.windows.net");
        dataSource.setUsername("smartsac");
        dataSource.setPassword("#Paysandu");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}