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
        slack.setDescricao(rs.getString("descricao"));

        return slack;

    }
}