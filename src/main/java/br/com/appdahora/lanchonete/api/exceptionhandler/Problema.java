package br.com.appdahora.lanchonete.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problema {
//    private Integer status;
    private LocalDateTime dataHora;
    private String mensagem;

//    private List<Campo> campos;
//    @AllArgsConstructor
//    @Getter
//    public static class Campo{
//        private String nome;
//        private String mensagem;
//    }
}
