/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto;

import com.github.britooo.looca.api.core.Looca;
import com.sptech.testeprojeto.integracao.slack.IntegracaoSlack;
import java.util.Timer;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import com.sptech.testeprojeto.tela.login.Maquina;
import com.sptech.testeprojeto.tela.login.MaquinaMapper;
import com.sptech.testeprojeto.tela.login.Operacao;
import com.sptech.testeprojeto.tela.login.OperacaoMapper;

/**
 *
 * @author wall
 */
// INSERIR TIMER E DELAY PARA CAPTURA DE DADOS A CADA 5
public class ValidacaoLogin {

    Looca looca = new Looca();
    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

    Sensors sensor = new SystemInfo().getHardware().getSensors();
    Integer threads = new SystemInfo().getOperatingSystem().getThreadCount();
    CentralProcessor processador = new SystemInfo().getHardware().getProcessor();
    GlobalMemory memoria = new SystemInfo().getHardware().getMemory();

    SystemInfo disk = new SystemInfo();

    // informações CPU
    String nomeCPU = looca.getProcessador().getNome();
    String numeroSerie = looca.getProcessador().getId();
    String fabricante = looca.getProcessador().getFabricante();

    String sistemaOperacional = looca.getSistema().getSistemaOperacional();

    SystemInfo info = new SystemInfo();

    Timer timer = new Timer();
    int cont = 1;
    int cont2 = 1;

    public void run() {
        String queryIdentificarMaquina = String.format("SELECT * FROM [dbo].[maquinas] WHERE hostname = '%s'",
                info.getHardware().getComputerSystem().getHardwareUUID());

        Maquina identificarMaquina = template.queryForObject(queryIdentificarMaquina, new MaquinaMapper());
        Double usoCPU = looca.getProcessador().getUso();
        Long freqCPU = looca.getProcessador().getFrequencia();

        Long usoRAM = looca.getMemoria().getEmUso();
        Long ramDisponivel = looca.getMemoria().getDisponivel();
        Double totalRAM = usoRAM.doubleValue() + ramDisponivel.doubleValue();

        Double percentualRAM = totalRAM.doubleValue() * (usoRAM / 100);

        String queryOperacao = String.format("SELECT * FROM [dbo].[operacoes]"
                + " WHERE (SELECT fk_operacao FROM [dbo].[maquinas] WHERE id_maquina = %d)"
                + "= id_operacao", identificarMaquina.getId());
        
        Operacao operacao = template.queryForObject(queryOperacao, new OperacaoMapper());

        // insert de log da CPU
        template.update(
                "INSERT INTO [dbo].[log_registros](uso, disponivel, frequencia, fk_maquina, fk_componente, data_hora) VALUES (?, ?, ?, ?, ?, GETDATE())",
                usoCPU,
                100 - usoCPU,
                freqCPU,
                identificarMaquina.getId(),
                10);

        System.out.println("\nDado De Processador Inserido -- x" + cont + "\n");
        cont = cont + 1;
        //// insert de log da RAM
        template.update(
                "INSERT INTO [dbo].[log_registros](uso, disponivel, frequencia, fk_maquina, fk_componente, data_hora) VALUES (?, ?, ?, ?, ?, GETDATE())",
                usoRAM,
                ramDisponivel,
                0,
                identificarMaquina.getId(),
                11);

        System.out.println("\nDado De Ram Inserido -- x" + cont2 + "\n");
        cont2 = cont2 + 1;
        // insert de log do disco
        // template.update("INSERT INTO log_registros VALUES (NULL, ?, ?, ?, NULL, ?",
        // usoDisco, discoDisponivel, freqDisco, 5);

        String codigoUrgencia;
        if (usoCPU > 0.0 && usoCPU <= 50.0) {
            codigoUrgencia = "Atenção";
            String descricao = String.format("A CPU da máquina %s está em %s."
                    + "\nUso de CPU: %.2f"
                    + "\nOperação: %s"
                    + "\nLocalidade: %s"
                    + "\nNome do gerente: %s",
                    identificarMaquina.getId(),
                    codigoUrgencia,
                    usoCPU,
                    operacao.getNome_operacao(),
                    operacao.getLocalidade(),
                    operacao.getNome_gerente()
            );

            String fkRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 10.0 AND uso <= 20.0 ORDER BY id_registro DESC";
            Registro idRegistro = template.queryForObject(fkRegistro, new RegistroMapper());

            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    idRegistro.getId());
            
            IntegracaoSlack.enviarMensagem(descricao);

        } else if (usoCPU > 50.0 && usoCPU <= 75.0) {
            codigoUrgencia = "Emergência";

            String descricao = String.format("A CPU da máquina %s está em %s."
                    + "\nUso de CPU: %.2f"
                    + "\nOperação: %s"
                    + "\nLocalidade: %s"
                    + "\nNome do gerente: %s",
                    identificarMaquina.getId(),
                    codigoUrgencia,
                    usoCPU,
                    operacao.getNome_operacao(),
                    operacao.getLocalidade(),
                    operacao.getNome_gerente()
            );

            String fkRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 10.0 AND uso <= 20.0 ORDER BY id_registro DESC";
            Registro idRegistro = template.queryForObject(fkRegistro, new RegistroMapper());
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    idRegistro.getId());
            IntegracaoSlack.enviarMensagem(descricao);

        } else if (usoCPU > 75.0) {

            codigoUrgencia = "Crítico";
            String descricao = String.format("A CPU máquina %s está em %s."
                    + "\nUso de CPU: %.2f"
                    + "\nOperação: %s"
                    + "\nLocalidade: %s"
                    + "\nNome do gerente: %s",
                    identificarMaquina.getId(),
                    codigoUrgencia,
                    usoCPU,
                    operacao.getNome_operacao(),
                    operacao.getLocalidade(),
                    operacao.getNome_gerente()
            );

            String fkRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 10.0 AND uso <= 20.0 ORDER BY id_registro DESC";
            Registro idRegistro = template.queryForObject(fkRegistro, new RegistroMapper());
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    idRegistro.getId());
            IntegracaoSlack.enviarMensagem(descricao);
        }

        if (percentualRAM >= 70.0 && percentualRAM < 80.0) {
            
            codigoUrgencia = "Alerta";
            String descricao = String.format("A memória RAM da máquina %s está em %s."
                    + "\nRAM: %.2f"
                    + "\nOperação: %s"
                    + "\nLocalidade: %s"
                    + "\nNome do gerente: %s",
                    identificarMaquina.getId(),
                    codigoUrgencia,
                    (usoRAM.doubleValue()/100000),
                    operacao.getNome_operacao(),
                    operacao.getLocalidade(),
                    operacao.getNome_gerente()
            );


            String fkRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Memoria Ram' AND uso > 85.0 ORDER BY id_registro DESC";
            Registro idRegistro = template.queryForObject(fkRegistro, new RegistroMapper());
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    idRegistro.getId());
            IntegracaoSlack.enviarMensagem(descricao);
        } 
        else if (percentualRAM >= 80.0 && percentualRAM < 90){
            codigoUrgencia = "Emergência";
            String descricao = String.format("A memória RAM da máquina %s está em nível de %s."
                    + "\nRAM: %.2f"
                    + "\nOperação: %s"
                    + "\nLocalidade: %s"
                    + "\nNome do gerente: %s",
                    identificarMaquina.getId(),
                    codigoUrgencia,
                    usoRAM.doubleValue()/100000,
                    operacao.getNome_operacao(),
                    operacao.getLocalidade(),
                    operacao.getNome_gerente()
            );


            String fkRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Memoria Ram' AND uso > 85.0 ORDER BY id_registro DESC";
            Registro idRegistro = template.queryForObject(fkRegistro, new RegistroMapper());
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    idRegistro.getId());
            IntegracaoSlack.enviarMensagem(descricao);
        }
        else if (percentualRAM > 90.0){
            codigoUrgencia = "Crítico";
            String descricao = String.format("A memória RAM da máquina %s está em nível %s."
                    + "\nRAM: %.2f"
                    + "\nOperação: %s"
                    + "\nLocalidade: %s"
                    + "\nNome do gerente: %s",
                    identificarMaquina.getId(),
                    codigoUrgencia,
                    usoRAM.doubleValue()/100000,
                    operacao.getNome_operacao(),
                    operacao.getLocalidade(),
                    operacao.getNome_gerente()
            );


            String fkRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Memoria Ram' AND uso > 85.0 ORDER BY id_registro DESC";
            Registro idRegistro = template.queryForObject(fkRegistro, new RegistroMapper());
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    idRegistro.getId());
            
            IntegracaoSlack.enviarMensagem(descricao);
        }

    }

}
