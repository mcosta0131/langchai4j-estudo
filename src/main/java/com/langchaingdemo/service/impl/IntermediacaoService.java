package com.langchaingdemo.service.impl;

import com.langchaingdemo.model.Intermediacao;
import com.langchaingdemo.repository.IntermediacaoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntermediacaoService {

    private final IntermediacaoRepository repository;
    
    @PostConstruct
    public void init() {
        // Cria um documento de exemplo se não existir
        if (repository.count() == 0) {
            Intermediacao exemplo = new Intermediacao(
                "12345678000199", // CNPJ de exemplo
                "ATIVO"
            );
            repository.save(exemplo);
        }
    }
    
    public Intermediacao salvar(Intermediacao intermediacao) {
        return repository.save(intermediacao);
    }
    
    public List<Intermediacao> listarTodos() {
        return repository.findAll();
    }
    
    public Intermediacao buscarPorCnpj(String cnpj) {
        return repository.findByCnpj(cnpj).orElse(null);
    }
}
