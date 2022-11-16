package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Empresa {
    @Column(length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    protected String nome;
    @EqualsAndHashCode.Include
    @Column(length = 14)
    protected String cnpj;
    @Column(length = 20)
    @JsonProperty("telefone")  //customizando na representação
    @NotBlank
    @Size(max = 20)
    protected String telefone;
    @Column(length = 40)
    @NotBlank
    @Email
    @Size(max = 40)
    protected String email;

    @Embedded
    private Endereco endereco;

}
