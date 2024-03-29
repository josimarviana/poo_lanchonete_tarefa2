package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.mapper.ClienteMapper;
import br.com.appdahora.lanchonete.api.model.ClienteModel;
import br.com.appdahora.lanchonete.api.model.input.ClienteInputModel;
import br.com.appdahora.lanchonete.api.model.xml.ClienteXmlWrapper;
import br.com.appdahora.lanchonete.domain.exception.ClienteNaoEncontradoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeEmUsoException;
import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.repository.ClienteRepository;
import br.com.appdahora.lanchonete.domain.service.ClienteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    // @CrossOrigin // para liberar o CORS
    // https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
    @GetMapping
    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClienteModel> listar(){

        return clienteMapper.toCollectionModel(clienteRepository.findAll());
    }
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ClienteXmlWrapper listarXML(){
        return new ClienteXmlWrapper(clienteRepository.findAll());
    }

   /* resposta simples
    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId){
        return clienteRepository.buscar(clienteId);
    }
   */

    @GetMapping("/{clienteId}")
    //Permite customizar a resposta HTTP, headers, código de resposta
    public ClienteModel buscar(@PathVariable Long clienteId){

        return clienteRepository.findById(clienteId)
                .map(cliente -> clienteMapper.toModel(cliente))
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

//        return clienteRepository.findById(clienteId)
//                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

       // Customizando um header
       // HttpHeaders headers = new HttpHeaders();
       // headers.add(HttpHeaders.LOCATION, "http://api.appdahora.com.br:8080/clientes");
       // return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public ClienteModel adicionar (@Valid @RequestBody ClienteInputModel clienteRequestModel){

        try {
            return clienteMapper.toModel(clienteService.salvar(clienteMapper.toEntity(clienteRequestModel)));
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long clienteId, @Valid  @RequestBody Cliente cliente){

        try{
            Optional<Cliente> clienteAtual =  clienteRepository.findById(clienteId);

            if (clienteAtual.isPresent()) {
                BeanUtils.copyProperties(cliente, clienteAtual.get(), "id", "endereco");
                Cliente clienteSalvo = clienteService.salvar(clienteAtual.get());
                return ResponseEntity.ok(clienteSalvo);
            }
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e){ //tratando violação de chave
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (EntidadeNaoEncontradaException e){ //tratando registro não encontrado
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    @PatchMapping("/{clienteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long clienteId,
                                              @Valid  @RequestBody Map<String, Object> campos, HttpServletRequest request){
        //Map para maior controle de chave e valor, onde valor é um objeto
        Optional<Cliente> clienteAtual =  clienteRepository.findById(clienteId); //recuperando do banco

        if (!clienteAtual.isPresent()){
            return ResponseEntity.notFound().build();
        }

        merge(campos, clienteAtual.get(), request);

        return atualizar(clienteId, clienteAtual.get());

    }

    private static void merge(Map<String, Object> dadosOrigem, Cliente clienteDestino, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try{
            ObjectMapper objectMapper = new ObjectMapper(); // converte json em objetos

            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true); // padrão, não precisa colocar
            Cliente clienteOrigem = objectMapper.convertValue(dadosOrigem, Cliente.class); // cria um objeto a partir do json

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                //valorPropriedade não é devidamente convertido para os tipos corretos, dá erro ao atribuir um bigint pq no json lê int
                Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade); //recupera a instância dos atributos
                field.setAccessible(true); //permite acesso aos membros da classe, mesmo sendo privados

                Object novoValor = ReflectionUtils.getField(field, clienteOrigem); //recupera o valos dos atributos

                ReflectionUtils.setField(field, clienteDestino, novoValor); //atribui os valores

            });
        }catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Cliente>  remover (@PathVariable Long clienteId){
        try{
            clienteService.remover(clienteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException e){ //tratando violação de chave
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (EntidadeNaoEncontradaException e){ //tratando registro não encontrado
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/por-nome")
    public List<Cliente> clientesPorNome(String nome) {

        return clienteRepository.findByNomeContaining(nome);
    }

    @GetMapping("/por-cpf")
    public ResponseEntity<Cliente>  clientesPorCpf(String cpf) {

        Cliente cliente =  clienteRepository.findByCpf(cpf);

        if(cliente != null) {
            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/por-email")
    public ResponseEntity<Cliente>  clientesPorEmail(String email) {

        Cliente cliente =  clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        if(cliente != null) {
            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/por-telefone")
    public ResponseEntity<Cliente>  clientesPorTelefone(String telefone) {

        Cliente cliente =  clienteRepository.findByTelefone(telefone);

        if(cliente != null) {
            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/por-data-nascimento")
    public List<Cliente>  clientesPorDataNascimento(LocalDate dataNascimento) {

        return  clienteRepository.findByDataNascimento(dataNascimento);

    }

}
