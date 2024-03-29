package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.PedidoNaoEncontradoException;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.model.StatusPedido;
import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import br.com.appdahora.lanchonete.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Pedido salvar (Pedido pedido){

        Long clienteId = pedido.getCliente().getId();
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isEmpty()) {  //verifica se tem algum cliente dentro do optional
            throw new PedidoNaoEncontradoException(clienteId);
        }
        pedido.setCliente(cliente.get());

        pedido.setStatusPedido(StatusPedido.PENDENTE);
        pedido.setDataSolicitacao(OffsetDateTime.now());

        return pedidoRepository.save(pedido);
        //TODO: Criar uma entrega automaticamente
    }

    public void remover(Long pedidoId){
        try {
            pedidoRepository.deleteById(pedidoId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new PedidoNaoEncontradoException(pedidoId);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removida, pois está em uso", pedidoId));
        }
    }

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }

    public void finalizar(Long pedidoId){
        Pedido pedido = buscarOuFalhar(pedidoId);

        pedido.finalizar();
        pedidoRepository.save(pedido);
    }

}
