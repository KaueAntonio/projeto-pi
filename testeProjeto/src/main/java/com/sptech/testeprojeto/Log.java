/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.testeprojeto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    
    public static void salvarLog(String textoLog) throws IOException {
        File diretorio = new File("logs");
        diretorio.mkdir();
        
        String arqData = DateTimeFormatter.ofPattern("yyy-MM-dd").format(LocalDateTime.now());
        String arqNome = String.format("logs/%s.txt", arqData);
        
        String dataHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss - ").format(LocalDateTime.now());
        
        File arq = new File(arqNome);
        Writer criadorDeArquivo = null;
        Writer Escrever = null;
        
        try(
                FileWriter criacaoDeArquivo = new FileWriter (arq, true);
                BufferedWriter escrever = new BufferedWriter(criadorDeArquivo);
                PrintWriter escritorDeArquivos = new PrintWriter(Escrever);){
            
            escritorDeArquivos.append("\n" + dataHora + textoLog);
        }catch (IOException erro){
            erro.printStackTrace();
        }
    }
}
