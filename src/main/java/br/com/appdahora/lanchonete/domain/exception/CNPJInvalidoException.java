package br.com.appdahora.lanchonete.domain.exception;

//desta forma é possível customizar vários httpStatus para a mesma exception
//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CNPJInvalidoException extends NegocioException {
    private static final long serialVersionUID = 1L;

    public CNPJInvalidoException(String mensagem){
        super(mensagem);
    }
}
