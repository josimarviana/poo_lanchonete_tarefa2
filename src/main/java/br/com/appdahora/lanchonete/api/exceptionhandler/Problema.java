package br.com.appdahora.lanchonete.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //não insere atributos nulos no json
public class Problema {

    private OffsetDateTime dataHora;
    private String mensagem;

}
