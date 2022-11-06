package br.com.appdahora.lanchonete.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponseModel {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

}
