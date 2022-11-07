package br.com.appdahora.lanchonete.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaEntregaModel {
    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
