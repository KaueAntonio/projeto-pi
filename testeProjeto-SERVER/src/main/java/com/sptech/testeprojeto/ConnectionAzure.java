package com.sptech.testeprojeto;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionAzure {
    private BasicDataSource dataSource;

    public ConnectionAzure() {
        dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://app_db:3306/smartsac");
        dataSource.setUsername("smartsac");
        dataSource.setPassword("smartsac");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
