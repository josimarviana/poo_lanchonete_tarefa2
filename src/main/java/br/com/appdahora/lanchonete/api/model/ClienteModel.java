package br.com.appdahora.lanchonete.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

}
