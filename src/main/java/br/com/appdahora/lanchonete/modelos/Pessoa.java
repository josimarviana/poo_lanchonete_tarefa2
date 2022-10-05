package br.com.appdahora.lanchonete.modelos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@MappedSuperclass
public abstract class Pessoa {
    @Column(length = 50, nullable = false)
    protected String nome;

    @Column(length = 11, nullable = false)
    protected String cpf;
    @Column(length = 13)
    protected String telefone;
    @Column(length = 40)
    protected String email;

    protected LocalDate dataNascimento;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Pessoa(){

    }
    public Pessoa(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }



    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}


