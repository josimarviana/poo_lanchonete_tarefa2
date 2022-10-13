package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.ProdutosXmlWrapper;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.model.Produto;
import br.com.appdahora.lanchonete.domain.repository.ProdutoRepository;
import br.com.appdahora.lanchonete.domain.service.CadastroProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;
    
    @GetMapping
    public List<Produto> listar(){
        return produtoRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ProdutosXmlWrapper listarXML(){
        return new ProdutosXmlWrapper(produtoRepository.listar());
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscar(@PathVariable Long produtoId){
        Produto produto =  produtoRepository.buscar(produtoId);
        if(produto !=null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public Produto adicionar (@RequestBody Produto produto){

        return cadastroProdutoService.salvar(produto);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long produtoId, @RequestBody Produto produto){
        Produto produtoAtual =  produtoRepository.buscar(produtoId);

        if(produtoAtual != null){
            // produtoAtual.setNome(produto.getNome()); //forma trabalhosa
            BeanUtils.copyProperties(produto, produtoAtual, "id");
            produtoAtual = cadastroProdutoService.salvar(produtoAtual);
            return ResponseEntity.ok(produtoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Cliente>  remover (@PathVariable Long produtoId){
        try{
            cadastroProdutoService.remover(produtoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException e){ //tratando violação de chave
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (EntidadeNaoEncontradaException e){ //tratando registro não encontrado
            return ResponseEntity.notFound().build();
        }
    }

}
