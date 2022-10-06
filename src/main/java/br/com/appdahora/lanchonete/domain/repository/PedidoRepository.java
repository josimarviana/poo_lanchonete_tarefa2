package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.model.Pedido;

import java.util.List;

public interface PedidoRepository {
    List<Pedido> todos();
    Pedido porId(Long id);
    Pedido adicionar(Pedido cliente);
    void remover(Pedido cliente);
}
