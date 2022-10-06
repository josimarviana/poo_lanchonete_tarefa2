package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import br.com.appdahora.lanchonete.domain.model.Cliente;
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
    public List<Cliente> todos() {
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
    @Override
    public Cliente porId(Long id) {
        return manager.find(Cliente.class, id);
    }
    @Override
    @Transactional
    public Cliente adicionar(Cliente cliente) {
        System.out.println("cliente: "+cliente.getId());
        return manager.merge(cliente);
    }
    @Override
    @Transactional
    public void remover(Cliente cliente) {
        System.out.println("cliente: "+cliente.getId());
        cliente = porId(cliente.getId());
        manager.remove(cliente);
    }
}
