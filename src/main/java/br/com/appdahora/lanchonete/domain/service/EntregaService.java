package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntregaNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.exception.ProdutoNaoEncontradoException;
import br.com.appdahora.lanchonete.domain.model.Entrega;
import br.com.appdahora.lanchonete.domain.model.StatusEntrega;
import br.com.appdahora.lanchonete.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega salvar (Entrega entrega){
        //regras de negócio
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);
        entrega.setDataSolicitacao(LocalDateTime.now());
        return entregaRepository.save(entrega);
    }

    public void remover(Long entregaId){
        try {
            entregaRepository.deleteById(entregaId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(entregaId);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Entrega de código %d não pode ser removida, pois está em uso", entregaId));
        }
    }

    public Entrega buscarOuFalhar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntregaNaoEncontradaException(entregaId));
    }

}
