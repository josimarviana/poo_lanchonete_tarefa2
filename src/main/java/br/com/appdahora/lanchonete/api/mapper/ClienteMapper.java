package br.com.appdahora.lanchonete.api.mapper;

import br.com.appdahora.lanchonete.api.model.request.ClienteRequestModel;
import br.com.appdahora.lanchonete.api.model.response.ClienteResponseModel;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper {
    private ModelMapper modelMapper;

    public ClienteResponseModel toModel(Cliente cliente){
        return modelMapper.map(cliente, ClienteResponseModel.class);
    }

    public List<ClienteResponseModel> toCollectionModel(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteRequestModel clienteRequestModel){
        return modelMapper.map(clienteRequestModel, Cliente.class);
    }
}
