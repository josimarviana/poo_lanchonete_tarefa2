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

    @CrossOrigin(origins = "http://localhost:8080/clientes")
    @GetMapping
    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> listar(){
        return clienteRepository.listar();
    }
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ClientesXmlWrapper listarXML(){
        return new ClientesXmlWrapper(clienteRepository.listar());
    }
    // /clientes/{clienteId}

    //@ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente>  buscar(@PathVariable Long clienteId){
        Cliente cliente =  clienteRepository.buscar(clienteId);
       // return ResponseEntity.status(HttpStatus.OK).body(cliente);
        return ResponseEntity.ok(cliente);
    }
    /*
    public Cliente buscar(@PathVariable Long clienteId){
        return clienteRepository.buscar(clienteId);
    }*/

}
