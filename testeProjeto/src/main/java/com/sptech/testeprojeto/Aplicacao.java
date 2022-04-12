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

        int delay = 500;
        int interval = 500;

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("\n\nProcessador: \nFrequência: " + processador.getProcessorIdentifier().getVendorFreq()
                        + "\nUso: " + looca.getProcessador().getUso() +
                        "\nFabricante : " + processador.getProcessorIdentifier().getVendor()
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

//                String inserirScript = "INSERT INTO dados VALUES (null, ?,?)";
//                template.update(inserirScript, sensor.getCpuTemperature(), looca.getProcessador().getFrequencia());

            }
        }, delay, interval);

    }
}
