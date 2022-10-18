package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> findAll();
    Restaurante findById(Long id);
    Restaurante save(Restaurante restaurante);
    void deleteById(Long id);
    List<Restaurante> consultarPorNome(String nome);

}
