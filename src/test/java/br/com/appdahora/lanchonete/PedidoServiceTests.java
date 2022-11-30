package br.com.appdahora.lanchonete;

import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.model.StatusPedido;
import br.com.appdahora.lanchonete.domain.service.PedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PedidoServiceTests {
    @Autowired
    PedidoService pedidoService;

    @Test
    public void deveFalhar_quandoFinalizaPedido_Cancelado() {
        // cenário
        Pedido pedido = pedidoService.buscarOuFalhar(1L);
        pedido.setStatusPedido(StatusPedido.CANCELADO);

        // ação e validação

        NegocioException erroEsperado =
                Assertions.assertThrows(NegocioException.class, () -> {
                    pedido.finalizar();
                });

        assertThat(erroEsperado).isNotNull();
    }

}
