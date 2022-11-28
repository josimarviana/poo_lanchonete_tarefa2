package br.com.appdahora.lanchonete.api.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //não insere atributos nulos no json
public class Problem {
    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;


//    private OffsetDateTime dataHora;
//    private String mensagem;

}
