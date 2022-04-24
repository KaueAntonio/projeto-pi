/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto;

import com.github.britooo.looca.api.core.Looca;
import com.mysql.cj.Query;
import java.util.List;
import java.util.Timer;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import com.sptech.testeprojeto.tela.login.Maquina;
import com.sptech.testeprojeto.tela.login.MaquinaMapper;
import java.sql.ResultSet;
import java.sql.Statement;

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
        if (usoCPU > 30.0 && usoCPU <= 50.0) {
            List maquina = template.queryForList("SELECT TOP (1) hostname FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 30.0 AND uso <= 50.0 ORDER BY id_registro DESC");
            List localidade = template.queryForList("SELECT TOP (1) localidade_maquina FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 30.0 AND uso <= 50.0 ORDER BY id_registro DESC");
            List operacao = template.queryForList("SELECT TOP (1) nome_operacao FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 30.0 AND uso <= 50.0 ORDER BY id_registro DESC");
            List gerente = template.queryForList("SELECT TOP (1) nome_gerente FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 30.0 AND uso <= 50.0 ORDER BY id_registro DESC");

            codigoUrgencia = "Alerta";
            String descricao = "";
            descricao = String.format("O estado da CPU da máquina está em alerta."
                    + " Favor verificar. \n"
                    + "Uso CPU: %.2f \n"
                    + "%s, %s, $s, %s", usoCPU, maquina, localidade, operacao, gerente);

//                String idRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 10.0 AND uso <= 20.0 ORDER BY id_registro DESC";
 
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    300
            );



        } else if (usoCPU > 2.0 && usoCPU <= 75.0){
            List maquina = template.queryForList("SELECT TOP (1) hostname FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 50.0 AND uso <= 75.0 ORDER BY id_registro DESC");
            List localidade = template.queryForList("SELECT TOP (1) localidade_maquina FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 50.0 AND uso <= 75.0 ORDER BY id_registro DESC");
            List operacao = template.queryForList("SELECT TOP (1) nome_operacao FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 50.0 AND uso <= 75.0 ORDER BY id_registro DESC");
            List gerente = template.queryForList("SELECT TOP (1) nome_gerente FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 50.0 AND uso <= 75.0 ORDER BY id_registro DESC");

            codigoUrgencia = "Emergência";
            String descricao = "";
            descricao = String.format("CPU em estado emergencial."
                    + " Favor verificar. \n"
                    + "Uso CPU: %.2f \n"
                    + "%s, %s, %s, %s", usoCPU, maquina, localidade, operacao, gerente);

//                String idRegistro = "SELECT TOP (1) id_registro FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 10.0 AND uso <= 20.0 ORDER BY id_registro DESC";
 
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    300
            );
        }else if (usoCPU > 75.0){
            List maquina = template.queryForList("SELECT TOP (1) hostname FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 75.0 ORDER BY id_registro DESC");
            List localidade = template.queryForList("SELECT TOP (1) localidade_maquina FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 75.0 ORDER BY id_registro DESC");
            List operacao = template.queryForList("SELECT TOP (1) nome_operacao FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 75.0 ORDER BY id_registro DESC");
            List gerente = template.queryForList("SELECT TOP (1) nome_gerente FROM [dbo].[log_registros] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[componentes] ON id_componente = fk_componente JOIN [dbo].[operacoes] ON id_operacao = fk_operacao WHERE tipo = 'Processador' AND uso > 75.0 ORDER BY id_registro DESC");

            codigoUrgencia = "Crítico";
            String descricao = "";
            descricao = String.format("O estado da CPU da máquina está crítico!"
                    + " Favor verificar. \n"
                    + "Uso CPU: %.2f \n"
                    + "%s, %s, $s, %s", usoCPU, maquina, localidade, operacao, gerente);

 
            template.update("INSERT INTO [dbo].[log_alertas] (codigo_urgencia, descricao, fk_registro) VALUES (?, ?, ?)",
                    codigoUrgencia,
                    descricao,
                    300
            );
        }
        
        if (usoRAM > 10){
            
        }
        
        

    } 

}


