package br.com.appdahora.lanchonete.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}