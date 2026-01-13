package com.langchaingdemo.repository;

import com.langchaingdemo.model.Intermediacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IntermediacaoRepository extends MongoRepository<Intermediacao, String> {
    Optional<Intermediacao> findByCnpj(String cnpj);
}
