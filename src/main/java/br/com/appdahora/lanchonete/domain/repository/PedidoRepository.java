package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataCriacao(LocalDate dataCriacao);
    List<Pedido> findByDataCriacaoBetween(LocalDate valorInicial, LocalDate valorFinal);
    List<Pedido> findByDataCriacaoBetweenAndClienteId(LocalDate valorInicial, LocalDate valorFinal, Long clienteId);
}
