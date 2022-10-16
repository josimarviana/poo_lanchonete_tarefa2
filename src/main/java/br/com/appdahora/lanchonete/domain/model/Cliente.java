package br.com.appdahora.lanchonete.domain.model;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonRootName("cliente") //muda root no xml
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cliente extends Pessoa{
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
