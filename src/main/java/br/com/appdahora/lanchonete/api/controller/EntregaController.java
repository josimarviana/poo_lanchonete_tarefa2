package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.domain.model.Entrega;
import br.com.appdahora.lanchonete.domain.repository.EntregaRepository;
import br.com.appdahora.lanchonete.domain.service.EntregaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public List<Entrega> listar() {

        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public Entrega buscar(@PathVariable Long entregaId) {
        return entregaService.buscarOuFalhar(entregaId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega adicionar(@RequestBody Entrega entrega) {

        return entregaService.salvar(entrega);
    }

    @PutMapping("/{entregaId}")
    public Entrega atualizar(@PathVariable Long entregaId,
                                            @RequestBody Entrega entrega) {
        Entrega entregaAtual = entregaService.buscarOuFalhar(entregaId);
        BeanUtils.copyProperties(entrega, entregaAtual, "id");
        return entregaService.salvar(entregaAtual);
    }

    @DeleteMapping("/{entregaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long entregaId) {

        entregaService.remover(entregaId);
    }


}