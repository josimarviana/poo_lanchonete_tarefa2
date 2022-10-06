package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.model.Pedido;

import java.util.List;

public interface PedidoRepository {
    List<Pedido> listar();
    Pedido buscar(Long id);
    Pedido salvar(Pedido cliente);
    void remover(Pedido cliente);
}
