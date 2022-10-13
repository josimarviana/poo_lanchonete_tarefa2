package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CadastroPedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar (Pedido pedido){

        try {
            return pedidoRepository.salvar(pedido);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Produto de código %d não pode ser encontrado", pedido.getId()));
        }
        catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Cliente %d de pedido não pode ser encontrado", pedido.getId()));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removida, pois está em uso", pedido.getId()));
        }

    }

    public void remover(Long pedidoId){
        try {
            pedidoRepository.remover(pedidoId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Produto de pedido %d não pode ser encontrado", pedidoId));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removida, pois está em uso", pedidoId));
        }
    }

}
