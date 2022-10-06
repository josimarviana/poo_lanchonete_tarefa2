package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> todos();
    Cliente porId(Long id);
    Cliente adicionar(Cliente cliente);
    void remover(Cliente cliente);
}
