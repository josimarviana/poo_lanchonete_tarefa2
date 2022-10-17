package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Empresa;
import br.com.appdahora.lanchonete.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmpresaRepository  {
    List<Empresa> findAll();
    Empresa findById(Long id);
    Empresa save(Empresa empresa);
    void deleteById(Long id);
    List<Empresa> consultarPorNome(String nome);

}
