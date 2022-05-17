/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.testeprojeto.integracao.slack;

/**
 *
 * @author aluno
 */
public class LogAlerta {
    private Integer id_log_alerta;
    private String codigo_urgencia;
    private String descricao;
    private Integer fk_registro;

    public Integer getId_log_alerta() {
        return id_log_alerta;
    }

    public void setId_log_alerta(Integer id_log_alerta) {
        this.id_log_alerta = id_log_alerta;
    }

    public String getCodigo_urgencia() {
        return codigo_urgencia;
    }

    public void setCodigo_urgencia(String codigo_urgencia) {
        this.codigo_urgencia = codigo_urgencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFk_registro() {
        return fk_registro;
    }

    public void setFk_registro(Integer fk_registro) {
        this.fk_registro = fk_registro;
    }
    
    
}
