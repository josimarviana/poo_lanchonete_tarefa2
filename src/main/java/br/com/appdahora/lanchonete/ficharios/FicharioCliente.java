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
    @PersistenceContext //mapeamento de persistência
    private EntityManager manager;

    public List<Cliente> listar(){
        TypedQuery<Cliente> query = manager.createQuery("from Cliente", Cliente.class);
        return query.getResultList();
    }
    @Transactional
    public Cliente adicionar(Cliente cliente){
        return manager.merge(cliente);

    }
}
