package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonRootName("entrega") //muda root no xml
public class Entrega {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal taxa;
    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;
    private LocalDateTime dataPedido;
    private LocalDateTime dataFinalizacao;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    @Embedded
    private Endereco endereco;
}
