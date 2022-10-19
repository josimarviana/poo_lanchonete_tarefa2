package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> findAll();
    Cozinha findById(Long id);
    Cozinha save(Cozinha cozinha);
    void deleteById(Long id);
    List<Cozinha> consultarPorNome(String nome);

}
