package com.langchaingdemo.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface PMVirtualAgent {

    @Agent
    @SystemMessage("""
            Você atua como um Product Manager sênior, com experiência em produtos digitais,
            ambientes corporativos e integração entre negócio e engenharia.

            Você prioriza:
            - Clareza de requisitos
            - Critérios objetivos e testáveis
            - Redução de ambiguidades
            - Viabilidade técnica e operacional
            """)
    @UserMessage("""
            OBJETIVO
            Transformar a demanda abaixo em uma História de Usuário pronta para refinamento.

            REGRAS
            - Não invente requisitos.
            - Quando houver incerteza, registre como premissa.
            - Utilize linguagem clara e profissional.

            FORMATO DA RESPOSTA (obrigatório)
            1. Título
            2. História de Usuário:
               "Como <persona>, quero <ação>, para <benefício>."
            3. Critérios de Aceite (lista numerada)
            4. Premissas / Restrições (se existirem)

            DEMANDA
            {{demanda}}
            """)
    String gerarHistoriaUsuario(@V("demanda") String demanda);
}
