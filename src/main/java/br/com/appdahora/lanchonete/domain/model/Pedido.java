package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition="datetime")
    private LocalDateTime dataCriacao;
    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition="datetime")
    private LocalDateTime dataAtualizacao;

    private LocalDate dataConfirmacao;
    private LocalDate dataEntrega;
    private LocalDate dataCancelamento;
    private StatusPedido statusPedido;

    @ManyToOne
    //@JoinColumn(name = "nome_customizado_coluna_join")
    private Cliente cliente;
    @OneToMany
    /* @JoinTable(name="nometabelameio", joinColumns = @JoinColumn(name="nomecolunachavePedido"),
    // inverseJoinColumns = @JoinColumn(name="nomecolunachaveItemPedido"))
    // para customizar nome da tabela e campos intermediários
    */
    private List<ItemPedido> itemPedido;

}
