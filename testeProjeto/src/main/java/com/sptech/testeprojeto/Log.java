/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.testeprojeto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Log {
    
    private DateTimeFormatter dateTimeLog = DateTimeFormatter.ofPattern("[yyyy/MM/dd HH:mm:ss]");
   
    private DateTimeFormatter dateTimeName = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String dataLog = dateTimeLog.format(LocalDateTime.now());
    private String dataNomeLog = dateTimeName.format(LocalDateTime.now());
    private String nomeLog = String.format("src/main/resources/%slogs.txt", dataNomeLog);


    public void criarLog(String message){
        try (
            FileWriter criarArquivo = new FileWriter(nomeLog, true);
            BufferedWriter buffer = new BufferedWriter(criarArquivo);
            PrintWriter gravarArq = new PrintWriter(buffer)
            ){
            gravarArq.append(String.format("Data: %s Mensagem: %s\n", dataLog, message));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

