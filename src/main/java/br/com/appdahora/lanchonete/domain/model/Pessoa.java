package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Pessoa {
    @Column(length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    protected String nome;
    @EqualsAndHashCode.Include
    //TODO: Erro 500 na validação de obrigatoriedade do cpf, ajustar
    @NotBlank
    @Size(min = 11, max = 11)
    @Column(length = 11)
    protected String cpf;
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
    protected OffsetDateTime dataNascimento;

    @Embedded
    protected Endereco endereco;

    @JsonIgnore //inibi na representação
    protected String usuario;
}


