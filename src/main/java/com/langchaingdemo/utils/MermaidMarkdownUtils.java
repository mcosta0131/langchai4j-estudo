package com.langchaingdemo.utils;

import java.time.LocalDateTime;

public class MermaidMarkdownUtils {
    private MermaidMarkdownUtils() {
    }

    public static String limparMarkdown(String conteudo) {
        return conteudo
                .replace("```mermaid", "")
                .replace("```", "")
                .trim();
    }

    public static String gerarMarkdown(String mermaidLimpo) {
        return """
                # Diagrama de Sequência

                Gerado automaticamente em: %s

                ```mermaid
                %s
                ```
                """.formatted(LocalDateTime.now(), mermaidLimpo);
    }
}
