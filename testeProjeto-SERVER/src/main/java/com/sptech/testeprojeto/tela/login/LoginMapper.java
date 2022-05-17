
package com.sptech.testeprojeto.tela.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class LoginMapper implements RowMapper<SelectLogin> {
    public SelectLogin mapRow(ResultSet rs, int nowNum) throws SQLException {
        SelectLogin login = new SelectLogin();
        login.setId(rs.getInt("id_operacao"));
        login.setNome(rs.getString("email_gerente"));
        login.setSenha(rs.getString("senha_gerente"));
        login.setFkOperacao(rs.getInt("fk_empresa"));

        return login;

    }
}