package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.mapper.OcorrenciaEntregaMapper;
import br.com.appdahora.lanchonete.api.model.OcorrenciaEntregaModel;
import br.com.appdahora.lanchonete.api.model.input.OcorrenciaEntregaInputModel;
import br.com.appdahora.lanchonete.domain.model.OcorrenciaEntrega;
import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.repository.PedidoRepository;
import br.com.appdahora.lanchonete.domain.service.OcorrenciaEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos/{pedidoId}/ocorrencias")
public class OcorrenciaEntregaController {

    @Autowired
    private OcorrenciaEntregaService ocorrenciaEntregaService;

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private OcorrenciaEntregaMapper ocorrenciaEntregaMapper;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaEntregaModel registrar(@PathVariable Long pedidoId,
                 @Valid @RequestBody OcorrenciaEntregaInputModel ocorrenciaInput){

        OcorrenciaEntrega ocorrenciaRegistrada = ocorrenciaEntregaService.registrar(pedidoId, ocorrenciaInput.getDescricao());
        return ocorrenciaEntregaMapper.toModel(ocorrenciaRegistrada);
    }
    @GetMapping
    public List<OcorrenciaEntregaModel> listar (@PathVariable Long pedidoId){
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        return ocorrenciaEntregaMapper.toCollectionModel(pedido.getOcorrenciasEntrega());
    }
}