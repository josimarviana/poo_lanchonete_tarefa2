package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("from Pedido p join p.cliente") //resolvendo problema n+1 com fetch join na JPQL
    List<Pedido> findAll();
    List<Pedido> findByDataSolicitacao(OffsetDateTime dataSolicitacao);
    List<Pedido> findByDataSolicitacaoBetween(OffsetDateTime valorInicial, OffsetDateTime valorFinal);
    List<Pedido> findByDataSolicitacaoBetweenAndClienteId(OffsetDateTime valorInicial, OffsetDateTime valorFinal, Long clienteId);

    @Query("from Pedido where cliente.nome = :nome") //usando query customizada
    List<Pedido> consultaporClienteNome(@Param("nome") String  nome);

    @Query("from Pedido where cliente.cpf = :cpf") //usando query customizada
    List<Pedido> consultaporClienteCpf(@Param("cpf") String  cpf);

    List<Pedido> findByRestauranteId(Long id);

}
