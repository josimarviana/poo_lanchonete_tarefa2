package br.com.appdahora.lanchonete.domain.repository;


import br.com.appdahora.lanchonete.domain.model.OcorrenciaEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaEntregaRepository extends JpaRepository<OcorrenciaEntrega, Long> {

}