package br.com.appdahora.lanchonete.api.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestModel {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
}
