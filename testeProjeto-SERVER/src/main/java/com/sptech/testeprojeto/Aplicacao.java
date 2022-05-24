
package com.sptech.testeprojeto;

import com.sptech.testeprojeto.integracao.slack.IntegracaoSlack;

public class Aplicacao {
    public static void main(String[] args) {
        ServerCatch.main(args);
        IntegracaoSlack.main(args);
    }
}
