package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.model.Empresa;
import br.com.appdahora.lanchonete.domain.model.Produto;
import br.com.appdahora.lanchonete.domain.repository.EmpresaRepository;
import br.com.appdahora.lanchonete.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa salvar (Empresa empresa){
        //regras de negócio
        return empresaRepository.save(empresa);
    }

    public void remover(Long empresaId){
        try {
            empresaRepository.deleteById(empresaId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Empresa de código %d não pode ser encontrado", empresaId));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Empresa de código %d não pode ser removida, pois está em uso", empresaId));
        }
    }

}
