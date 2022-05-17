/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto.tela.cliques;

/**
 *
 * @author LEONARDO
 */
public class Cliques {
    private Integer id_clique;
    private Integer cliques;
    private String data_clique;
    private Integer fk_maquina;

    public Integer getId_clique() {
        return id_clique;
    }

    public void setId_clique(Integer id_clique) {
        this.id_clique = id_clique;
    }

    public Integer getCliques() {
        return cliques;
    }

    public void setCliques(Integer cliques) {
        this.cliques = cliques;
    }

    public String getData_clique() {
        return data_clique;
    }

    public void setData_clique(String data_clique) {
        this.data_clique = data_clique;
    }

    public Integer getFk_maquina() {
        return fk_maquina;
    }

    public void setFk_maquina(Integer fk_maquina) {
        this.fk_maquina = fk_maquina;
    }
}
