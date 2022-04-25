/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto.integracao.slack;

/**
 *
 * @author wende
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class SlackMapper implements RowMapper<SlackTest> {
    public SlackTest mapRow(ResultSet rs, int nowNum) throws SQLException {
        SlackTest slack = new SlackTest();
        slack.setIdAlerta(rs.getInt("id_log_alerta"));
        slack.setCodigoUrgencia(rs.getString("codigo_urgencia"));
        slack.setDescricao(rs.getString("descricao"));
        slack.setFkRegistro(rs.getInt("fk_registro"));
       
        return slack;

    }
}