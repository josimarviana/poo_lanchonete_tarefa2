package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonRootName("cliente") //muda root no xml
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//TODO: Testar super
public class Cliente extends Pessoa{
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.ClienteId.class)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    //@Transient //não mapeia para BD
    //private String senha;
}
