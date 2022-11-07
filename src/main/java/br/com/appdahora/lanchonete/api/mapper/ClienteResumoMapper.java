package br.com.appdahora.lanchonete.api.mapper;

import br.com.appdahora.lanchonete.api.model.ClienteModel;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteResumoMapper {
    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente){
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
