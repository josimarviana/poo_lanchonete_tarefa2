package br.com.appdahora.lanchonete.api.controller;

import br.com.appdahora.lanchonete.api.model.EmpresasXmlWrapper;
import br.com.appdahora.lanchonete.domain.model.Restaurante;
import br.com.appdahora.lanchonete.domain.repository.RestauranteRepository;
import br.com.appdahora.lanchonete.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Equivalente a @Controller e @ResponseBody
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    
    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public EmpresasXmlWrapper listarXML(){
        return new EmpresasXmlWrapper(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        Restaurante restaurante =  restauranteRepository.findById(restauranteId);
        if(restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Altera o código de resposta HTTP
    public Restaurante adicionar (@RequestBody Restaurante restaurante){

        return cadastroRestauranteService.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        Restaurante restauranteAtual =  restauranteRepository.findById(restauranteId);

        if(restauranteAtual != null){
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            Restaurante restauranteSalva = cadastroRestauranteService.salvar(restauranteAtual);
            return ResponseEntity.ok(restauranteSalva);
        }
        return ResponseEntity.notFound().build();
    }
    //TODO: Inserir atualizar parcial no controller empresa
    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Restaurante>  remover (@PathVariable Long restauranteId){
        Restaurante restaurante =  restauranteRepository.findById(restauranteId);

        if(restaurante !=null) {
            restauranteRepository.deleteById(restauranteId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/por-nome")
    public List<Restaurante> empresaPorNome(@RequestParam("nome") String nome) {
        return restauranteRepository.consultarPorNome(nome);
    }

}
