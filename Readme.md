# Workflow de Planejamento com Agentes

## Visão Geral

Este documento descreve o fluxo completo do projeto de planejamento
automatizado utilizando agentes de IA. O objetivo é transformar uma
demanda de desenvolvimento em dois principais artefatos:

1.  Uma **História de Usuário estruturada**
2.  Um **Diagrama de Sequência em Mermaid**, documentado em arquivo
    Markdown

O fluxo é orquestrado por um serviço central que coordena a execução
sequencial dos agentes.

------------------------------------------------------------------------

## Componentes Envolvidos

### Controller

Responsável por: - Receber a demanda via API REST. - Acionar o serviço
de orquestração. - Retornar o resultado consolidado ao consumidor.

### PlanejamentoWorkflowService

Responsável por: - Orquestrar o fluxo entre os agentes. - Garantir a
execução sequencial. - Normalizar e persistir os artefatos gerados. -
Registrar logs de início, fim e duração de cada etapa.

### PMVirtualAgent

Responsável por: - Interpretar a demanda de negócio ou técnica. - Gerar
uma História de Usuário estruturada contendo: - Título - Descrição no
formato clássico (Como..., quero..., para...) - Critérios de aceite -
Premissas e restrições

### ArquitetoAgent

Responsável por: - Receber a História de Usuário. - Projetar a solução
técnica em alto nível. - Gerar um script Mermaid de diagrama de
sequência. - Respeitar regras rígidas de sintaxe Mermaid para garantir
renderização correta.

### Utilitários

-   **MermaidMarkdownUtils**: Limpa e gera conteúdo Markdown.
-   **MarkdownFileWriter**: Persiste o arquivo `.md` em disco.

------------------------------------------------------------------------

## Fluxo do Workflow

### Etapa 1 --- Recebimento da Demanda

1.  O cliente chama o endpoint REST.
2.  A demanda de desenvolvimento é enviada como texto.
3.  O Controller encaminha a demanda para o PlanejamentoWorkflowService.

------------------------------------------------------------------------

### Etapa 2 --- Geração da História de Usuário (PMVirtualAgent)

1.  O serviço orquestrador invoca o PMVirtualAgent.
2.  O agente interpreta a demanda.
3.  Uma História de Usuário estruturada é gerada.
4.  O resultado é retornado ao workflow.
5.  O tempo de execução é registrado em log.

Artefato gerado: - Texto estruturado da História de Usuário.

------------------------------------------------------------------------

### Etapa 3 --- Geração do Diagrama de Sequência (ArquitetoAgent)

1.  O workflow envia a História de Usuário ao ArquitetoAgent.
2.  O agente projeta o fluxo técnico da solução.
3.  Um script Mermaid válido é gerado.
4.  O conteúdo retornado é validado e normalizado.
5.  O tempo de execução é registrado em log.

Artefato gerado: - Script Mermaid de diagrama de sequência.

------------------------------------------------------------------------

### Etapa 4 --- Geração do Arquivo Markdown

1.  O Mermaid normalizado é incorporado em um template Markdown.
2.  O arquivo é salvo automaticamente em disco no diretório `docs/`.
3.  O caminho do arquivo gerado é retornado no response.

Artefato gerado: - Arquivo `.md` contendo o diagrama Mermaid.

------------------------------------------------------------------------

### Etapa 5 --- Retorno da Resposta

O serviço retorna ao Controller: - História de Usuário - Script Mermaid
normalizado - Caminho do arquivo Markdown gerado

O Controller devolve essas informações ao consumidor da API.

------------------------------------------------------------------------

## Diagrama Conceitual do Fluxo

``` mermaid
sequenceDiagram
    participant Cliente
    participant Controller
    participant Workflow
    participant PMAgent as "PMVirtualAgent"
    participant ArqAgent as "ArquitetoAgent"
    participant FileSystem

    Cliente ->> Controller: Envia demanda
    Controller ->> Workflow: executar(demanda)

    Workflow ->> PMAgent: gerarHistoriaUsuario(demanda)
    PMAgent -->> Workflow: História de Usuário

    Workflow ->> ArqAgent: gerarDiagramaSequencia(historia)
    ArqAgent -->> Workflow: Mermaid

    Workflow ->> FileSystem: Salva arquivo .md
    FileSystem -->> Workflow: Caminho do arquivo

    Workflow -->> Controller: Resultado consolidado
    Controller -->> Cliente: Response
```
------------------------------------------------------------------------

## Benefícios do Modelo

-   Separação clara de responsabilidades.
-   Fluxo determinístico e rastreável.
-   Facilidade de observabilidade e métricas.
-   Evolução simples para inclusão de novos agentes.
-   Geração automática de documentação técnica.
