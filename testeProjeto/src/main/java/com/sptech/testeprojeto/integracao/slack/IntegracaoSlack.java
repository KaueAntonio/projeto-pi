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

/**
 *
 * @author wende
 */
public class IntegracaoSlack {

    public static void main(String[] args) {
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String queryAlerta = "SELECT TOP(1) descricao FROM "
                + "[dbo].[log_alertas] JOIN [dbo].[log_registros] "
                + "ON fk_registro = id_registro ORDER BY id_log_alerta DESC";

        SlackTest teste = template.queryForObject(queryAlerta, new SlackMapper());
        String descricao = String.format("%s", teste.getDescricao());
        enviarMensagem(descricao);

    }

    public static void enviarMensagem(String message) {
        try {
            String webHookURL = "https://hooks.slack.com/services/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";
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
