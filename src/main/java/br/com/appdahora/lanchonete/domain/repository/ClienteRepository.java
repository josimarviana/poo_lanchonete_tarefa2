package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);
    Cliente findByCpf(String cpf);
    Cliente findByTelefone(String telefone);
    Cliente findByEmail(String email);
}
