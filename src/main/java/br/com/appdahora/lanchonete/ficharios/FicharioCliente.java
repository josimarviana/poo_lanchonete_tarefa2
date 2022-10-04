package br.com.appdahora.lanchonete.ficharios;

import br.com.appdahora.lanchonete.modelos.Cliente;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class FicharioCliente {
    @PersistenceContext
    private EntityManager manager;

    public List<Cliente> listar(){
       return manager.createQuery("from Cliente", Cliente.class).getResultList();
    }

}
