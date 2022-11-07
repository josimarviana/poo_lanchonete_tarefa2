package br.com.appdahora.lanchonete.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInputModel {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
}
