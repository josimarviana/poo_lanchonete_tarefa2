package br.com.appdahora.lanchonete.domain.exception;

public class EntregaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public EntregaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public EntregaNaoEncontradaException(Long entregaId){

        super(String.format("Não existe um cadastro de entrega com código %d", entregaId));
    }
}
