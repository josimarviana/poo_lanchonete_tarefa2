package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.xml.CozinhasXmlWrapper;
import br.com.appdahora.lanchonete.domain.model.Cozinha;
import br.com.appdahora.lanchonete.domain.repository.CozinhaRepository;
import br.com.appdahora.lanchonete.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;
    
    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXML(){
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    //TODO: Ajustar tratamento de exceções nos controllers
    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
        Cozinha cozinha =  cozinhaRepository.findById(cozinhaId);
        if(cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public Cozinha adicionar (@RequestBody Cozinha cozinha){

        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual =  cozinhaRepository.findById(cozinhaId);

        if(cozinhaAtual != null){
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            Cozinha cozinhaSalva = cozinhaService.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaSalva);
        }
        return ResponseEntity.notFound().build();
    }
    //TODO: Inserir atualizar parcial no controller empresa
    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha>  remover (@PathVariable Long cozinhaId){
        Cozinha cozinha =  cozinhaRepository.findById(cozinhaId);

        if(cozinha !=null) {
            cozinhaRepository.deleteById(cozinhaId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/por-nome")
    public List<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.consultarPorNome(nome);
    }

}
