
package com.sptech.testeprojeto;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {

    private BasicDataSource dataSource;

    public Connection() {
        dataSource = new BasicDataSource();
        dataSource​.setDriverClassName("org.h2.Driver");
        dataSource​.setUrl("localhost");
        dataSource​.setUsername("root");
        dataSource​.setPassword("urubu100");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}