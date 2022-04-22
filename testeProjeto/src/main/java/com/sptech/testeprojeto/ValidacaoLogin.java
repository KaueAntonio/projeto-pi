/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto;

import com.github.britooo.looca.api.core.Looca;
import java.util.List;
import java.util.Timer;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import com.sptech.testeprojeto.tela.login.Maquina;
import com.sptech.testeprojeto.tela.login.MaquinaMapper;

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
                "INSERT INTO [dbo].[log_registros](uso, disponivel, frequencia, fk_maquina, fk_componente) VALUES (?, ?, ?, ?, ?)",
                usoCPU,
                100 - usoCPU,
                freqCPU,
                identificarMaquina.getId(),
                10);

        System.out.println("\nDado De Processador Inserido -- x" + cont + "\n");
        cont = cont + 1;
        //// insert de log da RAM
        template.update(
                "INSERT INTO [dbo].[log_registros](uso, disponivel, frequencia, fk_maquina, fk_componente) VALUES (?, ?, ?, ?, ?)",
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
    }

}
