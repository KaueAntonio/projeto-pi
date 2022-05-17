
package com.sptech.testeprojeto.tela.login;

public class SelectLogin {
    private Integer id;
    private String nome;
    private String senha;
    private Integer fkOperacao;

    public Integer getFkOperacao() {
        return fkOperacao;
    }

    public void setFkOperacao(Integer fkOperacao) {
        this.fkOperacao = fkOperacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
