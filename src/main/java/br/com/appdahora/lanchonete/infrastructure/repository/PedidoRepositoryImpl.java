package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.model.Produto;
import br.com.appdahora.lanchonete.domain.repository.PedidoRepository;
import br.com.appdahora.lanchonete.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Pedido> todos() {
        return manager.createQuery("from Pedido", Pedido.class)
                .getResultList();
    }
    @Override
    public Pedido porId(Long id) {
        return manager.find(Pedido.class, id);
    }
    @Override
    @Transactional
    public Pedido adicionar(Pedido pedido) {
        System.out.println("pedido: "+pedido.getId());
        return manager.merge(pedido);
    }
    @Override
    @Transactional
    public void remover(Pedido pedido) {
        System.out.println("pedido: "+pedido.getId());
        pedido = porId(pedido.getId());
        manager.remove(pedido);
    }
}
