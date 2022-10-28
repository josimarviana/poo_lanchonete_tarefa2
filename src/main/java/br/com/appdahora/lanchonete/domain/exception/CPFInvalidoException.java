package br.com.appdahora.lanchonete.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//desta forma é possível customizar vários httpStatus para a mesma exception
public class CPFInvalidoException extends ResponseStatusException {
    private static final long serialVersionUID = 1L;

    public CPFInvalidoException(HttpStatus status, String mensagem){
        super(status, mensagem);

    }
    public CPFInvalidoException(String mensagem){
        this(HttpStatus.BAD_REQUEST, mensagem);
    }
}
