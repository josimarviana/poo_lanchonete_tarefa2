package br.com.appdahora.lanchonete.domain.repository;

import br.com.appdahora.lanchonete.domain.model.Cliente;
import br.com.appdahora.lanchonete.domain.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    List<Produto> listar();
    Produto buscar(Long id);
    Produto salvar(Produto produto);
    void remover(Produto produto);
}
