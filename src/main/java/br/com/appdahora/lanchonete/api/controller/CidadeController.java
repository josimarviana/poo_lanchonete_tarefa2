package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import br.com.appdahora.lanchonete.domain.model.Cidade;
import br.com.appdahora.lanchonete.domain.repository.CidadeRepository;
import br.com.appdahora.lanchonete.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade //ResponseEntity<Cidade>
                         buscar(@PathVariable Long cidadeId) {

        return cidadeService.buscarOuFalhar(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
        try {
            return cidadeService.salvar(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                            @RequestBody Cidade cidade) {

        Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return cidadeService.salvar(cidadeAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {

        cidadeService.remover(cidadeId);
    }


}
