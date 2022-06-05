
package com.sptech.testeprojeto;

import org.apache.commons.dbcp2.BasicDataSource;
import com.github.britooo.looca.api.core.Looca;
import com.sptech.testeprojeto.integracao.slack.IntegracaoSlack;
import java.util.Timer;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
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