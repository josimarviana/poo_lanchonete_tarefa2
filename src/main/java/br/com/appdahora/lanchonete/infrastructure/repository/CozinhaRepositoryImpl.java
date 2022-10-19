package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.model.Cozinha;
import br.com.appdahora.lanchonete.domain.model.Restaurante;
import br.com.appdahora.lanchonete.domain.repository.CozinhaRepository;
import br.com.appdahora.lanchonete.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> findAll() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }
    @Override
    public Cozinha findById(Long id) {
        return manager.find(Cozinha.class, id);
    }
    @Override
    @Transactional
    public Cozinha save(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        Cozinha cozinha = findById(id);
        if (cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);
    }
    public List<Cozinha> consultarPorNome(String nome) {
        return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
