package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.listar();
    }
}
