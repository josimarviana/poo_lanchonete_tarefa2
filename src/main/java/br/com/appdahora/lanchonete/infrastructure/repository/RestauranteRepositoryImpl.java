package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.model.Restaurante;
import br.com.appdahora.lanchonete.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> findAll() {
        return manager.createQuery("from Restaurante", Restaurante.class)
                .getResultList();
    }
    @Override
    public Restaurante findById(Long id) {
        return manager.find(Restaurante.class, id);
    }
    @Override
    @Transactional
    public Restaurante save(Restaurante restaurante) {
        return manager.merge(restaurante);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        Restaurante restaurante = findById(id);
        if (restaurante == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(restaurante);
    }
    public List<Restaurante> consultarPorNome(String nome) {
        return manager.createQuery("from Restaurante where nome like :nome", Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
