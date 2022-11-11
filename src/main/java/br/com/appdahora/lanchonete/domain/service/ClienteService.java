package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.CPFInvalidoException;
import br.com.appdahora.lanchonete.domain.exception.ClienteNaoEncontradoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import br.com.appdahora.lanchonete.domain.util.Valida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Valida valida;
    public Cliente salvar (Cliente cliente){
        //regras de negócio

        if(!valida.isCPF(cliente.getCpf())){
            throw new CPFInvalidoException(
                    String.format("Cliente não tem CPF válido")
            );
        }

        boolean emailEmUso =  clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if(emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com esse email");
        }

        return clienteRepository.save(cliente);
    }

    public void remover(Long clienteId){
        try {
            clienteRepository.deleteById(clienteId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ClienteNaoEncontradoException(
                    String.format("Cliente de código %d não pode ser encontrado", clienteId));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cliente de código %d não pode ser removida, pois está em uso", clienteId));
        }
    }
}
