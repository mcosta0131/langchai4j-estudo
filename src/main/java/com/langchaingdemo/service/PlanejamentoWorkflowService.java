package com.langchaingdemo.service;

import com.langchaingdemo.model.PlanejamentoResultado;

public interface PlanejamentoWorkflowService {
    PlanejamentoResultado executar(String demanda);
}
