package br.com.appdahora.lanchonete.api.mapper;

import br.com.appdahora.lanchonete.api.model.OcorrenciaEntregaModel;
import br.com.appdahora.lanchonete.api.model.input.OcorrenciaEntregaInputModel;
import br.com.appdahora.lanchonete.domain.model.OcorrenciaEntrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaEntregaMapper {
    private ModelMapper modelMapper;

    public OcorrenciaEntregaModel toModel(OcorrenciaEntrega ocorrenciaEntrega){
        return modelMapper.map(ocorrenciaEntrega, OcorrenciaEntregaModel.class);
    }

    public List<OcorrenciaEntregaModel> toCollectionModel(List<OcorrenciaEntrega> ocorrencias){
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public OcorrenciaEntrega toEntity(OcorrenciaEntregaInputModel ocorrenciaEntregaInputModel){
        return modelMapper.map(ocorrenciaEntregaInputModel, OcorrenciaEntrega.class);
    }
}
