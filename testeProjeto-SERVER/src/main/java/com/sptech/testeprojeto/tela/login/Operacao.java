/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.testeprojeto.tela.login;

/**
 *
 * @author aluno
 */
public class Operacao {
    private Integer id;
    private String nome_operacao;
    private String nome_gerente;
    private String localidade;


    public String getNome_operacao() {
        return nome_operacao;
    }

    public void setNome_operacao(String nome_operacao) {
        this.nome_operacao = nome_operacao;
    }
    
    public String getNome_gerente() {
        return nome_gerente;
    }

    public void setNome_gerente(String nome_gerente) {
        this.nome_gerente = nome_gerente;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
