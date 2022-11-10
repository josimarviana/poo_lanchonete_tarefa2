package br.com.appdahora.lanchonete.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//desta forma é possível customizar vários httpStatus para a mesma exception
//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CPFInvalidoException extends NegocioException {
    private static final long serialVersionUID = 1L;

    public CPFInvalidoException(String mensagem){
        super(mensagem);
    }
}
