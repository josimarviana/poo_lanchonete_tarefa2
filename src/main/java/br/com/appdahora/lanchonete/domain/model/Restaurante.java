package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40, nullable = false)
    private String nome;
    @Column(length = 100)
    private String descricao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cozinha cozinha;


    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;

}
