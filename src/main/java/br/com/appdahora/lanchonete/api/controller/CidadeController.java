package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.exceptionhandler.Problema;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import br.com.appdahora.lanchonete.domain.model.Cidade;
import br.com.appdahora.lanchonete.domain.repository.CidadeRepository;
import br.com.appdahora.lanchonete.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade //ResponseEntity<Cidade>
                         buscar(@PathVariable Long cidadeId) {
        return cadastroCidade.buscarOuFalhar(cidadeId);
    }

    @PostMapping
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cadastroCidade.salvar(cidade);
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                                       @RequestBody Cidade cidade) {

        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return cadastroCidade.salvar(cidadeAtual);
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {

        cadastroCidade.remover(cidadeId);
    }


}
