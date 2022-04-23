/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sptech.testeprojeto.integracao.slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

/**
 *
 * @author wende
 */
public class IntegracaoSlack {
    private static  String webHookURL = "https://hooks.slack.com/services/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";
    private static String slackChannel = "equipe-ti"; 
    public static void main(String[] args) {
        enviarMensagem("Teste de notificação v2");
    }
    
    public static void enviarMensagem(String message) {
        try {
            StringBuilder msgBuilder = new StringBuilder();
        
        msgBuilder.append(message);
        
        Payload payload = Payload.builder().channel(slackChannel).text(msgBuilder.toString()).build();
        
        WebhookResponse wbResp = Slack.getInstance().send(webHookURL, payload); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
