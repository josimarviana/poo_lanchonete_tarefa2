package br.com.appdahora.lanchonete.api.model;

import br.com.appdahora.lanchonete.api.model.ClienteResumoModel;
import br.com.appdahora.lanchonete.domain.model.ItemPedido;
import br.com.appdahora.lanchonete.domain.model.Restaurante;
import br.com.appdahora.lanchonete.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoModel {

    private Long id;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private LocalDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private StatusPedido status;
    private List<ItemPedido> itemPedido = new ArrayList<>();
    private ClienteResumoModel cliente;
    private Restaurante restaurante;


}
