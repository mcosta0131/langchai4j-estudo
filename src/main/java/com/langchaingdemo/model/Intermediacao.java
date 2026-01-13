package com.langchaingdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "intermediacao")
@Data
public class Intermediacao {
    @Id
    private String id;
    private String cnpj;
    private String statusIntermediacao; // ATIVO ou INATIVO

    public Intermediacao(String cnpj, String statusIntermediacao) {
        this.cnpj = cnpj;
        this.statusIntermediacao = statusIntermediacao;
    }
}
