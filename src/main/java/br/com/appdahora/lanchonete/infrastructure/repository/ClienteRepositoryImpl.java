package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cliente> listar() {
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
    public List<Cliente> consultarPorNome(String nome) {
        return manager.createQuery("from Cliente where nome like :nome", Cliente.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
    @Override
    public Cliente findByClienteId(Long id) {
        return manager.find(Cliente.class, id);
    }
    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {

        return manager.merge(cliente);
    }
    @Override
    @Transactional
    public void deleteByClienteID(Long id) {
        Cliente cliente = findByClienteId(id);
        if (cliente == null){
           throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cliente);
    }
}
