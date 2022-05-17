package com.sptech.testeprojeto.tela.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MaquinaMapper implements RowMapper<Maquina> {
    public Maquina mapRow(ResultSet rs, int nowNum) throws SQLException {
        Maquina maquina = new Maquina();
        maquina.setId(rs.getInt("id_maquina"));
        maquina.setHostname(rs.getString("hostname"));
        maquina.setSerial(rs.getString("serial_maquina"));
        maquina.setLocalicade(rs.getString("localidade_maquina"));
        maquina.setFkOperacao(rs.getInt("fk_operacao"));

        return maquina;

    }
}
