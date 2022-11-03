package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.CozinhaNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.model.Cozinha;
import br.com.appdahora.lanchonete.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar (Cozinha cozinha){
        //regras de negócio
        return cozinhaRepository.save(cozinha);
    }

    public void remover(Long cozinhaId){
        try {
            cozinhaRepository.deleteById(cozinhaId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(cozinhaId);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }
}
