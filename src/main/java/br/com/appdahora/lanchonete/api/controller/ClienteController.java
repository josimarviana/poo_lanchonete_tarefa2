package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.ClientesXmlWrapper;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import br.com.appdahora.lanchonete.domain.service.CadastroClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    // @CrossOrigin // para liberar o CORS
    // https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
    @GetMapping
    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> listar(){

        return clienteRepository.listar();
    }
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ClientesXmlWrapper listarXML(){
        return new ClientesXmlWrapper(clienteRepository.listar());
    }

   /* resposta simples
    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId){
        return clienteRepository.buscar(clienteId);
    }
   */

    @GetMapping("/{clienteId}")
    //Permite customizar a resposta HTTP, headers, código de resposta
    public ResponseEntity<Cliente>  buscar(@PathVariable Long clienteId){
        Cliente cliente =  clienteRepository.findByClienteId(clienteId);

        if(cliente !=null) {
            // return ResponseEntity.status(HttpStatus.OK).body(cliente); ou
            return ResponseEntity.ok(cliente);
        }

        //return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); ou
        return ResponseEntity.notFound().build();

       // Customizando um header
       // HttpHeaders headers = new HttpHeaders();
       // headers.add(HttpHeaders.LOCATION, "http://api.appdahora.com.br:8080/clientes");
       // return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public Cliente adicionar (@RequestBody Cliente cliente){

        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){

        try{
            Cliente clienteAtual =  clienteRepository.findByClienteId(clienteId);
            if (clienteAtual != null) {
                BeanUtils.copyProperties(cliente, clienteAtual, "id");
                clienteAtual = cadastroClienteService.salvar(clienteAtual);
                return ResponseEntity.ok(clienteAtual);
            }
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e){ //tratando violação de chave
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (EntidadeNaoEncontradaException e){ //tratando registro não encontrado
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    @PatchMapping("/{clienteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long clienteId, @RequestBody Map<String, Object> campos){

        Cliente clienteAtual =  clienteRepository.findByClienteId(clienteId);

        if (clienteAtual == null){
            return ResponseEntity.notFound().build();
        }

        merge(campos, clienteAtual);

        return atualizar(clienteId, clienteAtual);

    }

    private static void merge(Map<String, Object> camposOrigem, Cliente clienteDestino) {
        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {

            Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade);
            field.setAccessible(true);

            System.out.println(nomePropriedade+" = "+valorPropriedade);
            ReflectionUtils.setField(field, clienteDestino, valorPropriedade);

        });
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Cliente>  remover (@PathVariable Long clienteId){
        try{
            cadastroClienteService.remover(clienteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException e){ //tratando violação de chave
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (EntidadeNaoEncontradaException e){ //tratando registro não encontrado
            return ResponseEntity.notFound().build();
        }
    }
}
