package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
