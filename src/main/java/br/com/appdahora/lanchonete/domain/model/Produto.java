package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private Boolean ativo;
    //@JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY) // altera o modo agressivo de selects para o modo preguiçoso
    private Restaurante restaurante;
}
