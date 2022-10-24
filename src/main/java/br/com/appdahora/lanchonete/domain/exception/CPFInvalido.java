package br.com.appdahora.lanchonete.domain.exception;

public class CPFInvalido extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CPFInvalido(String mensagem){
        super(mensagem);
    }
}
