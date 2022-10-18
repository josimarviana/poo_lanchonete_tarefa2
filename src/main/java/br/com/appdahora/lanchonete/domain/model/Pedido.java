package br.com.appdahora.lanchonete.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private LocalDate dataCriacao;
    private LocalDate dataConfirmacao;
    private LocalDate dataEntrega;
    private LocalDate dataCancelamento;
    private StatusPedido statusPedido;

    @ManyToOne
    //@JoinColumn(name = "nome_customizado_coluna_join")
    private Cliente cliente;
    @OneToMany
    private List<ItemPedido> itensPedido;

}
