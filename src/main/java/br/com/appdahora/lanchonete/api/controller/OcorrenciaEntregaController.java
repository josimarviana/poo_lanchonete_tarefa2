package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.domain.model.Estado;
import br.com.appdahora.lanchonete.domain.model.OcorrenciaEntrega;
import br.com.appdahora.lanchonete.domain.repository.OcorrenciaEntregaRepository;
import br.com.appdahora.lanchonete.domain.service.OcorrenciaEntregaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaEntregaController {
    @Autowired
    private OcorrenciaEntregaRepository ocorrenciaEntregaRepository;

    @Autowired
    private OcorrenciaEntregaService ocorrenciaEntregaService;

    @GetMapping
    public List<OcorrenciaEntrega> listar() {

        return ocorrenciaEntregaRepository.findAll();
    }

    @GetMapping("/{ocorrenciaEntregaId}")
    public OcorrenciaEntrega buscar(@PathVariable Long ocorrenciaEntregaId) {
        return ocorrenciaEntregaService.buscarOuFalhar(ocorrenciaEntregaId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaEntrega adicionar(@RequestBody OcorrenciaEntrega ocorrenciaEntrega) {

        return ocorrenciaEntregaService.salvar(ocorrenciaEntrega);
    }

    @PutMapping("/{ocorrenciaEntregaId}")
    public OcorrenciaEntrega atualizar(@PathVariable Long ocorrenciaEntregaId,
                                            @RequestBody Estado estado) {
        OcorrenciaEntrega ocorrenciaEntregaAtual = ocorrenciaEntregaService.buscarOuFalhar(ocorrenciaEntregaId);
        BeanUtils.copyProperties(estado, ocorrenciaEntregaAtual, "id");
        return ocorrenciaEntregaService.salvar(ocorrenciaEntregaAtual);
    }

    @DeleteMapping("/{ocorrenciaEntregaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long ocorrenciaEntregaId) {

        ocorrenciaEntregaService.remover(ocorrenciaEntregaId);
    }

}