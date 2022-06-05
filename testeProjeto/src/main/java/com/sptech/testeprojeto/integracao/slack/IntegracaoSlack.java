/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto.integracao.slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.sptech.testeprojeto.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sptech.testeprojeto.integracao.slack.SlackMapper;
import com.sptech.testeprojeto.integracao.slack.SlackTest;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


/**
 *
 * @author wende
 */
public class IntegracaoSlack {

    public static void main(String[] args) {
        
//        String queryAlerta = "SELECT descricao FROM [dbo].[log_alertas]";
        
//        LogAlerta logAlerta = template.queryForObject(queryAlerta, new LogAlertaMapper());
        
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String queryAlerta = "SELECT TOP(1) * FROM "
                + "[dbo].[log_alertas] JOIN [dbo].[log_registros] "
                + "ON fk_registro = id_registro ORDER BY id_log_alerta DESC";
        

        List<SlackTest> teste = template.query(queryAlerta, new BeanPropertyRowMapper<>(SlackTest.class));
        System.out.println("String: " + teste.get(0).getDescricao());
        String descricao = String.format("%s", teste.get(0).getDescricao());
        enviarMensagem(descricao);

    }

    public static void enviarMensagem(String message) {
        try {
            String webHookURL = "";
            String slackChannel = "equipe-ti";
            StringBuilder msgBuilder = new StringBuilder();

            msgBuilder.append(message);

            Payload payload = Payload.builder().channel(slackChannel).text(msgBuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHookURL, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
