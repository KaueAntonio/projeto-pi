
package com.sptech.testeprojeto;

import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionDocker {
    private BasicDataSource dataSource;
    public ConnectionDocker() {
        dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://mysql:3306/smartsac");
        dataSource.setUsername("root");
        dataSource.setPassword("smartsac");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
