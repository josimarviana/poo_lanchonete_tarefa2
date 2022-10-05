package br.com.appdahora.lanchonete;

import br.com.appdahora.lanchonete.ficharios.FicharioCliente;
import br.com.appdahora.lanchonete.modelos.Cliente;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(LanchoneteApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FicharioCliente ficharioCliente = applicationContext.getBean(FicharioCliente.class);

        //listando todos os clientes
        List<Cliente> clientes = ficharioCliente.listar();

        for (Cliente cliente : clientes) {
            System.out.println(cliente.getId()+" - "+cliente.getNome());
        }

        //inserção de novo cliente
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Pedro");
        cliente1.setCpf("44444444444");
        cliente1.setEmail("pedro@gmail.com");
        cliente1.setTelefone("5478945612");

        ficharioCliente.salvar(cliente1);

        //busca por id
        Cliente cliente2 = ficharioCliente.buscar(1L);
        System.out.println(cliente2.getId()+ " - "+cliente2.getNome());

        //atualização de cliente

        Cliente cliente3 = new Cliente();
        cliente3.setId(1L);
        cliente3.setNome("Tereza");

        ficharioCliente.salvar(cliente3);

        //remoção de cliente
        Cliente cliente4 = new Cliente();
        cliente4.setId(1L);


        ficharioCliente.remover(cliente4);

    }
}
