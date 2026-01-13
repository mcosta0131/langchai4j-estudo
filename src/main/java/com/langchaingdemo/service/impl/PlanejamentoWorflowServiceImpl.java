package com.langchaingdemo.service.impl;

import com.langchaingdemo.agent.ArquitetoAgent;
import com.langchaingdemo.agent.PMVirtualAgent;
import com.langchaingdemo.model.PlanejamentoResultado;
import com.langchaingdemo.service.PlanejamentoWorkflowService;
import com.langchaingdemo.utils.MarkdownFileWriter;
import com.langchaingdemo.utils.MermaidMarkdownUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class PlanejamentoWorflowServiceImpl implements PlanejamentoWorkflowService {

    private static final Logger log =
            LoggerFactory.getLogger(PlanejamentoWorflowServiceImpl.class);

    private final PMVirtualAgent pmVirtualAgent;
    private final ArquitetoAgent arquitetoAgent;

    public PlanejamentoWorflowServiceImpl(PMVirtualAgent pmVirtualAgent, ArquitetoAgent arquitetoAgent) {
        this.pmVirtualAgent = pmVirtualAgent;
        this.arquitetoAgent = arquitetoAgent;
    }

    @Override
    public PlanejamentoResultado executar(String demanda) {

        log.info("🚀 Início do workflow de planejamento");
        log.debug("Demanda recebida: {}", demanda);

        long inicioWorkflow = System.currentTimeMillis();

        // ==============================
        // Etapa 1 — História de usuário
        // ==============================
        log.info("▶️ Iniciando geração da História de Usuário (PMVirtualAgent)");
        long inicioEtapa1 = System.currentTimeMillis();

        String historiaUsuario = pmVirtualAgent.gerarHistoriaUsuario(demanda);

        long duracaoEtapa1 = System.currentTimeMillis() - inicioEtapa1;
        log.info("✅ História de Usuário gerada em {} ms", duracaoEtapa1);
        log.debug("História de Usuário:\n{}", historiaUsuario);

        // ==============================
        // Etapa 2 — Diagrama arquitetural
        // ==============================
        log.info("▶️ Iniciando geração do Diagrama de Sequência (ArquitetoAgent)");
        long inicioEtapa2 = System.currentTimeMillis();

        String mermaidRaw = arquitetoAgent.gerarDiagramaSequencia(historiaUsuario);

        long duracaoEtapa2 = System.currentTimeMillis() - inicioEtapa2;
        log.info("✅ Diagrama gerado em {} ms", duracaoEtapa2);
        log.debug("Mermaid bruto:\n{}", mermaidRaw);

        // ==============================
        // Etapa 3 — Normalização Mermaid
        // ==============================
        log.info("▶️ Normalizando script Mermaid");
        long inicioEtapa3 = System.currentTimeMillis();

        String mermaidLimpo = MermaidMarkdownUtils.limparMarkdown(mermaidRaw);

        long duracaoEtapa3 = System.currentTimeMillis() - inicioEtapa3;
        log.info("✅ Mermaid normalizado em {} ms", duracaoEtapa3);

        // ==============================
        // Etapa 4 — Geração do Markdown
        // ==============================
        log.info("▶️ Gerando conteúdo Markdown");
        long inicioEtapa4 = System.currentTimeMillis();

        String markdown = MermaidMarkdownUtils.gerarMarkdown(mermaidLimpo);

        long duracaoEtapa4 = System.currentTimeMillis() - inicioEtapa4;
        log.info("✅ Markdown gerado em {} ms", duracaoEtapa4);

        // ==============================
        // Etapa 5 — Persistência em arquivo
        // ==============================
        log.info("▶️ Persistindo arquivo Markdown em disco");
        long inicioEtapa5 = System.currentTimeMillis();

        Path arquivo = MarkdownFileWriter.salvar(markdown);

        long duracaoEtapa5 = System.currentTimeMillis() - inicioEtapa5;
        log.info("✅ Arquivo Markdown salvo em {} ms", duracaoEtapa5);
        log.info("📄 Caminho do arquivo: {}", arquivo.toAbsolutePath());

        long duracaoTotal = System.currentTimeMillis() - inicioWorkflow;
        log.info("🏁 Workflow finalizado com sucesso em {} ms", duracaoTotal);

        return new PlanejamentoResultado(
                historiaUsuario,
                mermaidRaw,
                arquivo.toAbsolutePath().toString()
        );
    }
}
