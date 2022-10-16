package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> listar();

    List<Cliente> consultarPorNome(String nome);
    Cliente findByClienteId(Long id);
    Cliente salvar(Cliente cliente);
    void deleteByClienteID(Long id);
}
