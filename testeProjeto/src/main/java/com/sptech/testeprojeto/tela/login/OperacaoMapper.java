/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.testeprojeto.tela.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author aluno
 */
public class OperacaoMapper implements RowMapper<Operacao>{
    public Operacao mapRow(ResultSet rs, int nowNum) throws SQLException{
        Operacao operacao = new Operacao();
        operacao.setId(rs.getInt("id_operacao"));
        operacao.setLocalidade(rs.getString("localidade"));
        operacao.setNome_gerente(rs.getString("nome_gerente"));
        operacao.setNome_operacao(rs.getString("nome_operacao"));
        return operacao;
    }
            
    
    
}
