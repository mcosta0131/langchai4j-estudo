package com.langchaingdemo.tools;

import com.langchaingdemo.model.Intermediacao;
import com.langchaingdemo.service.impl.IntermediacaoService;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssistantTools {

    private final IntermediacaoService intermediacaoService;
    
    @Tool("Lista todos os CNPJs que estão com intermediação ativa. Não requer parâmetros.")
    public String listarCnpjsAtivos() {
        List<String> cnpjsAtivos = intermediacaoService.listarTodos().stream()
                .filter(i -> "ATIVO".equalsIgnoreCase(i.getStatusIntermediacao()))
                .map(i -> formatarCnpj(i.getCnpj()))
                .collect(Collectors.toList());
                
        if (cnpjsAtivos.isEmpty()) {
            return "Não há CNPJs com intermediação ativa no momento.";
        }
        
        return "CNPJs com intermediação ativa:\n" + String.join("\n", cnpjsAtivos);
    }

    @Tool("Verifica se um CNPJ específico realizou intermediação e retorna o status.")
    public String verificarIntermediacaoPorCnpj(String cnpj) {
        // Remove formatação do CNPJ para busca
        String cnpjNumerico = cnpj.replaceAll("\\D", "");
        
        Intermediacao intermediacao = intermediacaoService.buscarPorCnpj(cnpjNumerico);
        
        if (intermediacao == null) {
            return "O CNPJ " + formatarCnpj(cnpjNumerico) + " não realizou nenhuma intermediação.";
        }
        
        return "CNPJ " + formatarCnpj(cnpjNumerico) + " - Status: " + intermediacao.getStatusIntermediacao();
    }
    
    private String formatarCnpj(String cnpj) {
        // Formata o CNPJ no padrão XX.XXX.XXX/XXXX-XX
        return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
