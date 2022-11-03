package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.ProdutoNaoEncontradoException;
import br.com.appdahora.lanchonete.domain.model.Produto;
import br.com.appdahora.lanchonete.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar (Produto produto){
        //regras de negócio
        return produtoRepository.save(produto);
    }

    public void remover(Long produtoId){
        try {
            produtoRepository.deleteById(produtoId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(produtoId);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removida, pois está em uso", produtoId));
        }
    }

}
