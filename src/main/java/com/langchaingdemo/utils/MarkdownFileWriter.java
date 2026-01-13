package com.langchaingdemo.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class MarkdownFileWriter {

    private MarkdownFileWriter() {
    }

    public static Path salvar(String markdown) {
        try {
            String fileName = "diagrama-sequencia-"
                    + LocalDateTime.now().toString().replace(":", "-")
                    + ".md";

            Path path = Path.of("docs", fileName);

            Files.createDirectories(path.getParent());
            Files.writeString(path, markdown);

            return path;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar arquivo Markdown", e);
        }
    }
}
