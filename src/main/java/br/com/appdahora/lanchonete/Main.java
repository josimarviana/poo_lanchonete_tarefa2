package br.com.appdahora.lanchonete;

import br.com.appdahora.lanchonete.ficharios.FicharioCliente;
import br.com.appdahora.lanchonete.modelos.Cliente;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = (ApplicationContext) new SpringApplicationBuilder(LanchoneteApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FicharioCliente ficharioCliente = applicationContext.getBean(FicharioCliente.class);

        List<Cliente> clientes = ficharioCliente.listar();

        for (Cliente cliente : clientes) {
            System.out.println(cliente.getNome());
        }
    }
}
