package br.com.appdahora.lanchonete.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public PedidoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long produtoId) {
        this(String.format("Não existe um cadastro de pedido com código %d", produtoId));
    }
}
