package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.EmpresasXmlWrapper;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.model.Empresa;
import br.com.appdahora.lanchonete.domain.repository.EmpresaRepository;
import br.com.appdahora.lanchonete.domain.service.CadastroEmpresaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CadastroEmpresaService cadastroEmpresaService;
    
    @GetMapping
    public List<Empresa> listar(){
        return empresaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public EmpresasXmlWrapper listarXML(){
        return new EmpresasXmlWrapper(empresaRepository.findAll());
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<Empresa> buscar(@PathVariable Long empresaId){
        Empresa empresa =  empresaRepository.findById(empresaId);
        if(empresa != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public Empresa adicionar (@RequestBody Empresa empresa){

        return cadastroEmpresaService.salvar(empresa);
    }

    @PutMapping("/{empresaId}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long empresaId, @RequestBody Empresa empresa){
        Empresa empresaAtual =  empresaRepository.findById(empresaId);

        if(empresaAtual != null){
            // produtoAtual.setNome(produto.getNome()); //forma trabalhosa
            BeanUtils.copyProperties(empresa, empresaAtual, "id");
            Empresa empresaSalva = cadastroEmpresaService.salvar(empresaAtual);
            return ResponseEntity.ok(empresaSalva);
        }
        return ResponseEntity.notFound().build();
    }
    //TODO: Inserir atualizar parcial no controller empresa
    @DeleteMapping("/{empresaId}")
    public ResponseEntity<Empresa>  remover (@PathVariable Long empresaId){
        Empresa empresa =  empresaRepository.findById(empresaId);

        if(empresa !=null) {
            empresaRepository.deleteById(empresaId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/por-nome")
    public List<Empresa> empresaPorNome(@RequestParam("nome") String nome) {
        return empresaRepository.consultarPorNome(nome);
    }

}
