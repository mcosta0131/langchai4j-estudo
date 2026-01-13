package com.langchaingdemo.controller;

import dev.langchain4j.service.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistant")
public class IntermediacaoController {

    private final com.langchaingdemo.AssistantAiService assistantAiService;

    public IntermediacaoController(com.langchaingdemo.AssistantAiService assistantAiService) {
        this.assistantAiService = assistantAiService;
    }

    @PostMapping()
    public String askAssistant(@RequestBody String userMessage) {
        Result<String> result = assistantAiService.handleRequest(userMessage);
        return result.content();
    }
}
