/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto;

import com.github.britooo.looca.api.core.Looca;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;

import java.util.TimerTask;
import com.sptech.testeprojeto.tela.login.Login;
import javax.xml.transform.Templates;


/**
 *
 * @author wall
 */

// INSERIR TIMER E DELAY PARA CAPTURA DE DADOS A CADA 5
public class ValidacaoLogin { 

    
    Looca looca = new Looca();
    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

    List internet = new SystemInfo().getHardware().getNetworkIFs();
    List soundcard = new SystemInfo().getHardware().getSoundCards();
    Sensors sensor = new SystemInfo().getHardware().getSensors();
    List gpu = new SystemInfo().getHardware().getGraphicsCards();
    Integer threads = new SystemInfo().getOperatingSystem().getThreadCount();
    List disks = new SystemInfo().getHardware().getDiskStores();
    CentralProcessor processador = new SystemInfo().getHardware().getProcessor();
    GlobalMemory memoria = new SystemInfo().getHardware().getMemory();

    SystemInfo disk = new SystemInfo();

    List discos = disk.getHardware().getDiskStores();

    

    //        informações CPU
    String nomeCPU = looca.getProcessador().getNome();
    String numeroSerie = looca.getProcessador().getId();
    String fabricante = looca.getProcessador().getFabricante();

    String sistemaOperacional = looca.getSistema().getSistemaOperacional();

    int delay = 5000;
    int interval = 5000;
    
    Timer timer = new Timer();
    
    
    public void run() {
        Double usoCPU = looca.getProcessador().getUso();
        Long freqCPU = looca.getProcessador().getFrequencia();

        Long usoRAM = looca.getMemoria().getEmUso();

        SystemInfo si = new SystemInfo();

        Long ramDisponivel = looca.getMemoria().getDisponivel();

        System.out.println("\n\nProcessador: \nFrequência: " + processador.getProcessorIdentifier().getVendorFreq()
                + "\nUso: " + looca.getProcessador().getUso()
                + "\nFabricante : " + processador.getProcessorIdentifier().getVendor()
                + "\nMicroArquitetura: " + processador.getProcessorIdentifier().getMicroarchitecture()
                + processador.getMaxFreq()
                + "\nTemperatura: " + sensor.getCpuTemperature()
                + "\nVoltagem: " + sensor.getCpuVoltage()
                + "\nFan Speed: " + sensor.getFanSpeeds()
                + "\n\nMemória Ram: \nUso: " + looca.getMemoria().getEmUso() + "\nDisponível: "
                + memoria.getAvailable()
                + "\n\nSistema: \nArquitetura: " + looca.getSistema().getArquitetura()
                + "\nSO: " + looca.getSistema().getSistemaOperacional()
                + "\n\nTempo Ligado: " + looca.getSistema().getTempoDeAtividade()
                + "\n\nInternet: " + internet + "\n\nPlaca de Som: " + soundcard
                + "\n\nGPU: " + gpu
                + "\n\nDisco: "
                + "\nDiskStores: " + disks);
        
        

//               insert de log da CPU
//        template.update("INSERT INTO log_registros VALUES (?, ?, ?, ?, ?)",
//                usoCPU,
//                100 - usoCPU,
//                freqCPU,
//                3);
////              insert de log da RAM
//        template.update("INSERT INTO log_registros VALUES (?, ?, ?, ?, ?)",
//                usoRAM,
//                ramDisponivel,
//                0,
//                4);
//               insert de log do disco
//               template.update("INSERT INTO log_registros VALUES (NULL, ?, ?, ?, NULL, ?",
//                        usoDisco, discoDisponivel, freqDisco, 5);
    }

}
