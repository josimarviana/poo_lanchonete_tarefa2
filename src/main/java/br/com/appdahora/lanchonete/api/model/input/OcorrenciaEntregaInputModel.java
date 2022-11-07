package br.com.appdahora.lanchonete.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OcorrenciaEntregaInputModel {
    @NotBlank
    private String descricao;
}
