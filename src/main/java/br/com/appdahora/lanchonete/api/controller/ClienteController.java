package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.ClientesXmlWrapper;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

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
        Cliente cliente =  clienteRepository.buscar(clienteId);

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
        return clienteRepository.salvar(cliente);
    }
}
