package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.CNPJInvalidoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.RestauranteNaoEncontradoException;
import br.com.appdahora.lanchonete.domain.model.Restaurante;
import br.com.appdahora.lanchonete.domain.repository.RestauranteRepository;
import br.com.appdahora.lanchonete.domain.util.Valida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private Valida valida;

    public Restaurante salvar (Restaurante restaurante){
        //regras de negócio
        if(!valida.isCNPJ(restaurante.getCnpj())){
            throw new CNPJInvalidoException(
                    String.format("Restaurante não tem CNPJ válido")
            );
        }
        return restauranteRepository.save(restaurante);
    }

    public void remover(Long empresaId){
        try {
            restauranteRepository.deleteById(empresaId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(empresaId);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Empresa de código %d não pode ser removida, pois está em uso", empresaId));
        }
    }

}
