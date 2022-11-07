package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import br.com.appdahora.lanchonete.domain.model.OcorrenciaEntrega;
import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.model.StatusPedido;
import br.com.appdahora.lanchonete.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OcorrenciaEntregaService {
    private static final String MSG_OCORRENCIA_ENTREGA_EM_USO
            = "Ocorrência de entrega de código %d não pode ser removida, pois está em uso";
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public OcorrenciaEntrega registrar(Long pedidoId, String descricao) {

        Pedido pedido = buscar(pedidoId);
        pedido.adicionarOcorrencia(descricao);

        return pedido.adicionarOcorrencia(descricao);
    }

    public Pedido buscar(Long pedidoId){
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new NegocioException("Pedido não encontrado"));
    }

    public void finalizar(Long pedidoId){
        Pedido pedido = buscar(pedidoId);

        pedido.finalizar();
        pedidoRepository.save(pedido);
    }

}
