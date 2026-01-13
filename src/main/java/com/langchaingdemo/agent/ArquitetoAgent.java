package com.langchaingdemo.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface ArquitetoAgent {

    @Agent
    @SystemMessage("""
        Você atua como um Arquiteto de Software experiente, especializado em sistemas distribuídos,
        APIs REST, mensageria e integração entre serviços.

        Você prioriza:
        - Clareza arquitetural
        - Separação de responsabilidades
        - Fluxos previsíveis
        - Boas práticas de integração
        """)
    @UserMessage("""
        Receba a História de Usuário abaixo e produza um diagrama de sequência no formato Mermaid,
        representando a solução técnica proposta.

        REGRAS FUNCIONAIS
        - Considere uma arquitetura baseada em serviços.
        - Modele os principais componentes (Cliente, API, Serviços, Banco, Mensageria, etc).
        - Represente apenas interações relevantes para o fluxo principal.
        - Não adicione explicações em texto.

        REGRAS OBRIGATÓRIAS DE SINTAXE MERMAID
        - Nunca utilize espaços em identificadores Mermaid.
        - Sempre utilize o padrão:
          participant Alias as "Nome Legível"
        - O Alias deve conter apenas letras e números (ex: ApiGateway, ServicoClientes).
        - Nunca utilize nomes diretamente como:
          "Frontend Cliente", "API Gateway", "Banco de Dados".
        - Não utilize tipos especiais como: database, queue, actor, boundary.
        - Todos os participantes devem possuir alias explícito.
        - Sempre inicie o diagrama com exatamente:
          sequenceDiagram
        - Retorne apenas o Mermaid puro, sem markdown, sem ``` e sem qualquer texto adicional.

        FORMATO DA RESPOSTA (obrigatório)
        O primeiro token da resposta deve ser:
        sequenceDiagram

        HISTÓRIA DE USUÁRIO
        {{historia}}
        """)
    String gerarDiagramaSequencia(@V("historia") String historia);
}
