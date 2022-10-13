package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.PedidosXmlWrapper;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.model.Pedido;
import br.com.appdahora.lanchonete.domain.model.Produto;
import br.com.appdahora.lanchonete.domain.repository.PedidoRepository;
import br.com.appdahora.lanchonete.domain.service.CadastroPedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroPedidoService cadastroPedidoService;
    
    @GetMapping
    public List<Pedido> listar(){
        return pedidoRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public PedidosXmlWrapper listarXML(){
        return new PedidosXmlWrapper(pedidoRepository.listar());
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long pedidoId){
        Pedido pedido =  pedidoRepository.buscar(pedidoId);
        if(pedido !=null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public ResponseEntity<Pedido>  adicionar (@RequestBody Pedido pedido){

        try{
            cadastroPedidoService.salvar(pedido);
            return ResponseEntity.ok(pedido);
        } catch (EntidadeEmUsoException e){ //tratando violação de chave
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (EntidadeNaoEncontradaException e){ //tratando registro não encontrado
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long pedidoId, @RequestBody Pedido pedido){
        Pedido pedidoAtual =  pedidoRepository.buscar(pedidoId);

        if(pedidoAtual != null){
            // produtoAtual.setNome(produto.getNome()); //forma trabalhosa
            BeanUtils.copyProperties(pedido, pedidoAtual, "id");
            pedidoAtual = cadastroPedidoService.salvar(pedidoAtual);
            return ResponseEntity.ok(pedidoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Pedido>  remover (@PathVariable Long pedidoId){
        try{
            cadastroPedidoService.remover(pedidoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException e){ //tratando violação de chave
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (EntidadeNaoEncontradaException e){ //tratando registro não encontrado
            return ResponseEntity.notFound().build();
        }
    }

}
