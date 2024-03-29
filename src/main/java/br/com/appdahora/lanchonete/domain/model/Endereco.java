package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Endereco {
    //@Column(name = "endereco_logradouro")
    private String logradouro;
    //@Column(length = 10, name = "endereco_numero")
    private String numero;
    //@Column(length = 40, name = "endereco_complemento")
    private String complemento;
    //@Column(length = 40, name = "endereco_bairro")
    private String  bairro;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cidade cidade;
    @Column(length = 8, name = "cep")
    private String cep;
}
