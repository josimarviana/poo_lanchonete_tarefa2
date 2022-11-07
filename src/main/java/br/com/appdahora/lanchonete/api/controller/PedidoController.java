package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.xml.PedidoXmlWrapper;
import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.repository.PedidoRepository;
import br.com.appdahora.lanchonete.domain.service.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

//TODO: Alterar mapeamento para PedidoModel
@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping
    public List<Pedido> listar(){
        return pedidoRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public PedidoXmlWrapper listarXML(){
        return new PedidoXmlWrapper(pedidoRepository.findAll());
    }

    @GetMapping("/{pedidoId}")
    public Pedido buscar(@PathVariable Long pedidoId){
        return pedidoService.buscarOuFalhar(pedidoId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public Pedido  adicionar (@Valid @RequestBody Pedido pedido){

        return pedidoService.salvar(pedido);
    }

    @PutMapping("/{pedidoId}")
    public Pedido atualizar(@PathVariable Long pedidoId, @RequestBody Pedido pedido){
        Pedido pedidoAtual = pedidoService.buscarOuFalhar(pedidoId);
        BeanUtils.copyProperties(pedido, pedidoAtual, "id");
        return pedidoService.salvar(pedidoAtual);

    }
    //TODO: Inserir atualizar parcial no controller pedido
    @DeleteMapping("/{pedidoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long pedidoId){
        pedidoService.remover(pedidoId);
    }

    @GetMapping("/por-cliente-nome")
    public List<Pedido>  clientesPorNome(@RequestParam("nome") String nome) {

        return  pedidoRepository.consultaporClienteNome(nome);

    }

    @GetMapping("/por-cliente-cpf")
    public List<Pedido>  clientesPorCpf(@RequestParam("cpf") String cpf) {

        return  pedidoRepository.consultaporClienteCpf(cpf);

    }
    @GetMapping("/por-data-criacao")
    public List<Pedido>  pedidosPorDataSolicitacao(OffsetDateTime dataSolicitacao) {

        return  pedidoRepository.findByDataSolicitacao(dataSolicitacao);

    }
    @GetMapping("/por-data-criacao-entre")
    public List<Pedido>   pedidosPorDataCriacaoEntre(OffsetDateTime dataSolicitacaoInicial, OffsetDateTime dataSolicitacaoFinal) {

        return  pedidoRepository.findByDataSolicitacaoBetween(dataSolicitacaoInicial, dataSolicitacaoFinal);

    }

    @GetMapping("/por-data-criacao-entre-e-cliente")
    public List<Pedido>  pedidosPorDataCriacaoEntreECliente(OffsetDateTime dataCriacaoInicial, OffsetDateTime dataCriacaoFinal, Long clienteId) {

        return  pedidoRepository.findByDataSolicitacaoBetweenAndClienteId(dataCriacaoInicial, dataCriacaoFinal, clienteId);

    }

    @GetMapping("/por-restaurante")
    public List<Pedido>  pedidosPorRestaurante(Long restaurante) {

        return  pedidoRepository.findByRestauranteId(restaurante);

    }
}
