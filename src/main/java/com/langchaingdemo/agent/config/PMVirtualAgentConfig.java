package com.langchaingdemo.agent.config;

import com.langchaingdemo.agent.PMVirtualAgent;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PMVirtualAgentConfig {

    @Value("${gemini.api-key}")
    private String geminiApiKey;

    @Value("${gemini.model}")
    private String geminiModel;

    @Bean
    public PMVirtualAgent pmVirtualAgent() {
        return AgenticServices.agentBuilder(PMVirtualAgent.class)
                .chatModel(GoogleAiGeminiChatModel.
                        builder()
//                        .temperature(0.7) Criatividade do agente
                        .apiKey(geminiApiKey)
                        .modelName(geminiModel)
                        .build())
                .outputKey("demanda")
                .build();
    }
}
