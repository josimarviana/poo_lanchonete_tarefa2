package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Pessoa {
    @Column(length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    protected String nome;
    @EqualsAndHashCode.Include
    @Column(length = 11)
    protected String cpf;
    @Column(length = 20)
    @JsonProperty("fone")  //customizando na representação
    @NotBlank
    @Size(max = 20)
    protected String telefone;
    @Column(length = 40)
    @NotBlank
    @Email
    @Size(max = 40)
    protected String email;
    protected LocalDate dataNascimento;
    @JsonIgnore //inibi na representação
    protected String usuario;
}


