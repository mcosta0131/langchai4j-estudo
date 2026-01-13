package com.langchaingdemo.controller;

import com.langchaingdemo.service.PMVirtualService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agentes/pmvirtual")
public class PMVirtualController {

    private final PMVirtualService pmVirtualService;

    public PMVirtualController(PMVirtualService pmVirtualService) {
        this.pmVirtualService = pmVirtualService;
    }

    @GetMapping
    public String gerarHistoriaUsuario(@RequestBody String demanda) {
        return pmVirtualService.escreverHistoria(demanda);
    }
}
