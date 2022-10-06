package br.com.appdahora.lanchonete.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;
}
