package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ClienteRepositoryImpl implements ClienteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cliente> listar() {
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
    @Override
    public Cliente buscar(Long id) {
        return manager.find(Cliente.class, id);
    }
    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {

        return manager.merge(cliente);
    }
    @Override
    @Transactional
    public void remover(Long id) {
        Cliente cliente = buscar(id);
        if (cliente == null){
           throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cliente);
    }
}
