package br.com.appdahora.lanchonete.api.exceptionhandler;

import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice //trata exceções de forma global
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(MethodArgumentNotValidException ex, WebRequest request){

        Problema problema = new Problema();
        HttpStatus status =  HttpStatus.NOT_FOUND;
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setMensagem(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(MethodArgumentNotValidException ex, WebRequest request){
        Problema problema = new Problema();
        HttpStatus status =  HttpStatus.BAD_REQUEST;
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setMensagem(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(){
//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem("Tipo de Mídia não aceito").build();
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> MethodArgumentNotValidException(){
//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem("Dados incorretos").build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
//    }

    @Override
    protected ResponseEntity<Object>
        handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                     HttpHeaders headers, HttpStatus status, WebRequest request){

        List<Problema.Campo> campos = new ArrayList<>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Problema.Campo(nome, mensagem));
        }

//                Problema problema = Problema.builder()
//                .status(status.value())
//                .dataHora(LocalDateTime.now())
//                .mensagem(ex.getMessage()).build();

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setMensagem("Um ou mais campos estão inválidos");
        problema.setCampos(campos);

        return handleExceptionInternal(ex, problema, headers, status, request);

    }

}
