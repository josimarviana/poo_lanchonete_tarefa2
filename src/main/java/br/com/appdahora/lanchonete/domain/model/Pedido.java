package br.com.appdahora.lanchonete.domain.model;

import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
    private BigDecimal taxaEntrega;
    private BigDecimal valorTotal;
    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition="datetime")
    private OffsetDateTime dataSolicitacao;
    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition="datetime")
    private OffsetDateTime dataAtualizacao;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCancelamento;
    @Enumerated(value = EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusPedido statusPedido;

    @Embedded
    private Endereco endereco;

    //@JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedido = new ArrayList<>();
    /* @JoinTable(name="nometabelameio", joinColumns = @JoinColumn(name="nomecolunachavePedido"),
    // inverseJoinColumns = @JoinColumn(name="nomecolunachaveItemPedido"))
    // para customizar nome da tabela e campos intermediários
    */

    //@JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY) // altera o modo agressivo de selects para o modo preguiçoso
    //@JoinColumn(name = "nome_customizado_coluna_join")
    @Valid
    @ConvertGroup(from = Default.class, to=ValidationGroups.ClienteId.class)
    @NotNull
    private Cliente cliente;

    //@JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY) // altera o modo agressivo de selects para o modo preguiçoso
    private Restaurante restaurante;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<OcorrenciaEntrega> ocorrenciasEntrega = new ArrayList<>();

    public OcorrenciaEntrega adicionarOcorrencia(String descricao){
        OcorrenciaEntrega ocorrenciaEntrega = new OcorrenciaEntrega();
        ocorrenciaEntrega.setDescricao(descricao);
        ocorrenciaEntrega.setDataRegistro(OffsetDateTime.now());
        ocorrenciaEntrega.setPedido(this);

        this.getOcorrenciasEntrega().add(ocorrenciaEntrega);

        return ocorrenciaEntrega;
    }

    public void finalizar(){
        if(naoPodeSerFinalizado()) {

            throw new NegocioException("Pedido não pode ser finalizado");
        }
        setStatusPedido(StatusPedido.FINALIZADO);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizado(){
        return StatusPedido.PENDENTE.equals(getStatusPedido());
        //return getStatusPedido().equals(StatusPedido.PENDENTE);
    }

    public boolean naoPodeSerFinalizado(){
        return !podeSerFinalizado();
    }
}
