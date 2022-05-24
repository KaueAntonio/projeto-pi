package com.sptech.testeprojeto;

import com.sptech.testeprojeto.Connection;
import com.sptech.testeprojeto.ValidacaoLogin;
import com.sptech.testeprojeto.tela.cliques.TelaAtendimento;
import com.sptech.testeprojeto.tela.login.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import oshi.SystemInfo;

public class ServerCatch {
        public static void main(String[] args) {

                JdbcTemplate template = new JdbcTemplate(new Connection().getDataSource());
                JdbcTemplate template2 = new JdbcTemplate(new ConnectionAzure().getDataSource());
                SystemInfo info = new SystemInfo();
                String maquina = String.format("%s", info.getHardware().getComputerSystem().getSerialNumber());
                String query = String.format("SELECT * FROM [dbo].[maquinas]"
                                + " WHERE serial_maquina = '%s'", maquina);
                List lista = template.queryForList(query);
                if (lista.isEmpty()) {
                        String queryInsert = String.format("INSERT INTO [dbo].[maquinas]" +
                                        "(hostname, serial_maquina, localidade_maquina, fk_operacao)" +
                                        "VALUES ('%s', '%s', 'Equipe 2', %d);",
                                        info.getHardware().getComputerSystem().getHardwareUUID(),
                                        info.getHardware().getComputerSystem().getSerialNumber(), 10);
                        template.update(queryInsert);
                        // template2.update(queryInsert);

                        Timer timer = new Timer();
                        ValidacaoLogin login = new ValidacaoLogin();
                        timer.scheduleAtFixedRate(new TimerTask() {
                                public void run() {
                                        login.run();
                                }
                        }, 5000, 5000);

                } else {
                        Timer timer = new Timer();
                        ValidacaoLogin login = new ValidacaoLogin();
                        timer.scheduleAtFixedRate(new TimerTask() {
                                public void run() {
                                        login.run();
                                }
                        }, 5000, 5000);
                }
        }
}
