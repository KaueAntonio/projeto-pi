package com.sptech.testeprojeto;

import com.github.britooo.looca.api.core.Looca;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.Sensors;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sptech.testeprojeto.tela.login.Login;

public class Aplicacao {

    public static void main(String[] args) {
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
        
        
        Login login = new Login();

        int delay = 500;
        int interval = 500;

        Timer timer = new Timer();

//        informações máquina
        System.out.println(looca.getSistema());

//        informações CPU
        String nomeCPU = looca.getProcessador().getNome();
        String numeroSerie = looca.getProcessador().getId();
        String fabricante = looca.getProcessador().getFabricante();

        String sistemaOperacional = looca.getSistema().getSistemaOperacional();

        //                insert na tabela funcionarios;
//        template.update("INSERT INTO funcionarios VALUES (?, ?, null)",
//                "Thais", "12345678910");

//                 insert na tabela maquinas;
//        template.update("INSERT INTO maquinas VALUES (?, ?, ?, ?)",
//                "DESKTOP-2D4ULK6",
//                sistemaOperacional,
//                "Equipe 1",
//                4);
              
//
////                insert na tabela componentes (CPU)
//                template.update("INSERT INTO componentes VALUES (?, ?, ?, ?)",
//                        nomeCPU,
//                        numeroSerie,
//                        "teste",
//                        2);
//
////                (RAM)
//                template.update("INSERT INTO componentes VALUES (?, ?, ?, ?)",
//                        "RAM",
//                        "01234567",
//                        "Crucial",
//                        2);
////
//////                (DISCO)
//                template.update("INSERT INTO componentes VALUES (?, ?, ?, ?)",
//                        "HD",
//                        "11134567",
//                        "Kingstom",
//                        2);

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Double usoCPU = looca.getProcessador().getUso();
                Long freqCPU = looca.getProcessador().getFrequencia();

                Long usoRAM = looca.getMemoria().getEmUso();
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

////                insert de log da CPU
//                template.update("INSERT INTO log_registros VALUES (?, ?, ?, ?, ?)",
//                        usoCPU,
//                        0,
//                        freqCPU,
//                        0, 
//                        3);

////                insert de log da RAM
                template.update("INSERT INTO log_registros VALUES (?, ?, ?, ?, ?)",
                        usoRAM,
                        ramDisponivel,
                        0,
                        0,
                        4);
                
//                insert de log do disco
//                template.update("INSERT INTO log_registros VALUES (NULL, ?, ?, ?, NULL, ?",
//                        usoDisco, discoDisponivel, freqDisco, 2);
            }
        }, delay, interval);

    }
}
