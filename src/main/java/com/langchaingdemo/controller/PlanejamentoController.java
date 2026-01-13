package com.langchaingdemo.controller;

import com.langchaingdemo.model.PlanejamentoResultado;
import com.langchaingdemo.service.PlanejamentoWorkflowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workflow/planejamento")
public class PlanejamentoController {
    private final PlanejamentoWorkflowService workflowService;

    public PlanejamentoController(PlanejamentoWorkflowService workflowService) {
        this.workflowService = workflowService;
    }


    @PostMapping
    public PlanejamentoResultado executar(@RequestBody String demanda) {
        return workflowService.executar(demanda);
    }
}
