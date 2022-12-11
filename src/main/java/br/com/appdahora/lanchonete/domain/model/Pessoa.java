package br.com.appdahora.lanchonete.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50)
    protected String nome;
    @EqualsAndHashCode.Include
    //TODO: Erro 500 na validação de obrigatoriedade do cpf, ajustar
    @NotBlank
    @Size(min = 11, max = 11, message = "O cpf precisa ter 11 dígitos")
    @Column(length = 11)
    @CPF
    protected String cpf;
    @Column(length = 20)
    @JsonProperty("telefone")  //customizando na representação
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 20)
    protected String telefone;
    @Column(length = 40)
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato do Email inválido")
    @Size(max = 40)
    protected String email;
    protected OffsetDateTime dataNascimento;

    //TODO: Adapter para data

    @Embedded
    protected Endereco endereco;

    @JsonIgnore //inibe na representação
    protected String usuario;
}


