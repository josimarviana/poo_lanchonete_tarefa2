package br.com.appdahora.lanchonete.ficharios;

import br.com.appdahora.lanchonete.modelos.Cliente;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class FicharioCliente {
    @PersistenceContext
    private EntityManager manager;

    public List<Cliente> listar() {
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

    public Cliente buscar(Long id) {
        return manager.find(Cliente.class, id);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return manager.merge(cliente);
    }

    @Transactional
    public void remover(Cliente cliente) {
        cliente = buscar(cliente.getId());
        manager.remove(cliente);
    }

}
