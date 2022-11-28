package br.com.appdahora.lanchonete;

import br.com.appdahora.lanchonete.domain.model.Estado;
import br.com.appdahora.lanchonete.domain.service.EstadoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EstadoServiceIntegrationTests {

    @Autowired
    private EstadoService estadoService;

    @Test
    public void testarCadastroEstadoComSucesso() {
        // cenário
        Estado novoEstado = new Estado();
        novoEstado.setNome("Alagoas");
        novoEstado.setSigla("AL");

        // ação
        novoEstado = estadoService.salvar(novoEstado);

        // validação
        assertThat(novoEstado).isNotNull();
        assertThat(novoEstado.getId()).isNotNull();
    }

    @Test
    public void testarCadastroEstadoSemNome() {
        Estado novoEstado = new Estado();
        novoEstado.setNome(null);

        ConstraintViolationException erroEsperado =
                Assertions.assertThrows(ConstraintViolationException.class, () -> {
                    estadoService.salvar(novoEstado);
                });

        assertThat(erroEsperado).isNotNull();
    }

}
