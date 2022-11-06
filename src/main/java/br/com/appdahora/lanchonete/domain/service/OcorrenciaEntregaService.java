package br.com.appdahora.lanchonete.domain.service;

import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.OcorrenciaEntregaNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.model.OcorrenciaEntrega;
import br.com.appdahora.lanchonete.domain.repository.OcorrenciaEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class OcorrenciaEntregaService {
    private static final String MSG_OCORRENCIA_ENTREGA_EM_USO
            = "Ocorrência de entrega de código %d não pode ser removida, pois está em uso";
    @Autowired
    private OcorrenciaEntregaRepository ocorrenciaEntregaRepository;

    public OcorrenciaEntrega salvar(OcorrenciaEntrega ocorrenciaEntrega) {

        return ocorrenciaEntregaRepository.save(ocorrenciaEntrega);
    }

    public void remover(Long ocorrenciaEntregaId) {
        try {
            ocorrenciaEntregaRepository.deleteById(ocorrenciaEntregaId);

        } catch (EmptyResultDataAccessException e) {
            throw new OcorrenciaEntregaNaoEncontradaException(ocorrenciaEntregaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_OCORRENCIA_ENTREGA_EM_USO, ocorrenciaEntregaId));
        }
    }
    public OcorrenciaEntrega buscarOuFalhar(Long ocorrenciaEntregaId) {
        return ocorrenciaEntregaRepository.findById(ocorrenciaEntregaId)
                .orElseThrow(() -> new OcorrenciaEntregaNaoEncontradaException(ocorrenciaEntregaId));
    }
}
