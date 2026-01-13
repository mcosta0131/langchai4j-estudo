package com.langchaingdemo.service.impl;

import com.langchaingdemo.agent.PMVirtualAgent;
import com.langchaingdemo.service.PMVirtualService;
import org.springframework.stereotype.Service;

@Service
public class PMVirtualServiceImpl implements PMVirtualService {
    private final PMVirtualAgent pmVirtualAgent;

    public PMVirtualServiceImpl(PMVirtualAgent pmVirtualAgent) {
        this.pmVirtualAgent = pmVirtualAgent;
    }

    @Override
    public String escreverHistoria(String demanda) {
        return pmVirtualAgent.gerarHistoriaUsuario(demanda);
    }
}
