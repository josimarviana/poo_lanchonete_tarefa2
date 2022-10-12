package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.ProdutosXmlWrapper;
import br.com.appdahora.lanchonete.domain.model.Produto;
import br.com.appdahora.lanchonete.domain.repository.ProdutoRepository;
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
        return produtoRepository.salvar(produto);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long produtoId, @RequestBody Produto produto){
        Produto produtoAtual =  produtoRepository.buscar(produtoId);

        if(produtoAtual != null){
            // produtoAtual.setNome(produto.getNome()); //forma trabalhosa
            BeanUtils.copyProperties(produto, produtoAtual, "id");
            produtoAtual = produtoRepository.salvar(produtoAtual);
            return ResponseEntity.ok(produtoAtual);
        }
        return ResponseEntity.notFound().build();

    }
}
