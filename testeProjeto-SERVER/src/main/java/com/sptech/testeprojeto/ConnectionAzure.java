package com.sptech.testeprojeto;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionAzure {
    private BasicDataSource dataSource;

    public ConnectionAzure() {
        dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://172.17.0.2/smartsac");
        dataSource.setUsername("smartsac");
        dataSource.setPassword("smartsac");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
