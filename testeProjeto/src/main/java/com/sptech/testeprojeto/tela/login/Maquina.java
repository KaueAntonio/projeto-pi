package com.sptech.testeprojeto.tela.login;

public class Maquina {
    private Integer id;
    private String hostname;
    private String serial;
    private String localicade;
    private Integer fkOperacao;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public String getSerial() {
        return serial;
    }
    public void setSerial(String serial) {
        this.serial = serial;
    }
    public String getLocalicade() {
        return localicade;
    }
    public void setLocalicade(String localicade) {
        this.localicade = localicade;
    }
    public Integer getFkOperacao() {
        return fkOperacao;
    }
    public void setFkOperacao(Integer fkOperacao) {
        this.fkOperacao = fkOperacao;
    }
}
