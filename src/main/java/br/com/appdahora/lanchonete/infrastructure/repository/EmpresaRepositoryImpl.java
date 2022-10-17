package br.com.appdahora.lanchonete.infrastructure.repository;

import br.com.appdahora.lanchonete.domain.model.Empresa;
import br.com.appdahora.lanchonete.domain.repository.EmpresaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmpresaRepositoryImpl implements EmpresaRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Empresa> findAll() {
        return manager.createQuery("from Empresa", Empresa.class)
                .getResultList();
    }
    @Override
    public Empresa findById(Long id) {
        return manager.find(Empresa.class, id);
    }
    @Override
    @Transactional
    public Empresa save(Empresa empresa) {
        System.out.println("empresa: "+empresa.getId());
        return manager.merge(empresa);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        Empresa empresa = findById(id);
        if (empresa == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(empresa);
    }


    public List<Empresa> consultarPorNome(String nome) {
        return manager.createQuery("from Empresa where nome like :nome", Empresa.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
