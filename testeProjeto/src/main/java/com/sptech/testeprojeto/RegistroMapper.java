/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto;

/**
 *
 * @author wall
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RegistroMapper implements RowMapper<Registro> {
    public Registro mapRow(ResultSet rs, int nowNum) throws SQLException {
        Registro registro = new Registro();
        registro.setId(rs.getInt("id_registro"));
        return registro;

    }
}