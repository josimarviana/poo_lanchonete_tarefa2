package br.com.appdahora.lanchonete.domain.exception;

public class OcorrenciaEntregaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public OcorrenciaEntregaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public OcorrenciaEntregaNaoEncontradaException(Long estadoId){

        super(String.format("Não existe um cadastro de ocorrência de entrega com código %d", estadoId));
    }
}
