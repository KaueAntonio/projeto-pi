/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.testeprojeto.integracao.slack;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author aluno
 */
public class LogAlertaMapper implements RowMapper<LogAlerta>{
    public LogAlerta mapRow(ResultSet rs, int nowNum) throws SQLException {
        LogAlerta log = new LogAlerta();
        
        log.setId_log_alerta(rs.getInt("id_log_alerta"));
        log.setDescricao(rs.getString("descricao"));
        log.setFk_registro(rs.getInt("fk_registro"));
        
        return log;
    }
}
