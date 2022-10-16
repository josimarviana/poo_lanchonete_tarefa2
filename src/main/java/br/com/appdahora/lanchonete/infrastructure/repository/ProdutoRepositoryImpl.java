package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.model.Produto;
import br.com.appdahora.lanchonete.domain.repository.ProdutoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Produto> listar() {
        return manager.createQuery("from Produto", Produto.class)
                .getResultList();
    }
    @Override
    public Produto buscar(Long id) {
        return manager.find(Produto.class, id);
    }
    @Override
    @Transactional
    public Produto salvar(Produto produto) {
        System.out.println("produto: "+produto.getId());
        return manager.merge(produto);
    }
    @Override
    @Transactional
    public void remover(Long id) {
        Produto produto = buscar(id);
        if (produto == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(produto);
    }
}
