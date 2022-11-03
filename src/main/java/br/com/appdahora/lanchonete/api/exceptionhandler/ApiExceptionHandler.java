package br.com.appdahora.lanchonete.api.exceptionhandler;

import br.com.appdahora.lanchonete.domain.exception.EntidadeNaoEncontradaException;
import br.com.appdahora.lanchonete.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler //extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){

        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
    }
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e){
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(){
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem("Tipo de Mídia não aceito").build();
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(){
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem("Dados incorretos").build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
    }

//    @Override
//    protected ResponseEntity<Object>
//        handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
//              HttpHeaders headers, HttpStatus status, WebRequest request){
////        Problema problema = new Problema();
////        problema.setStatus(status.value());
////        problema.setDataHora(LocalDateTime.now());
////        problema.setMensagem("Um ou mais campos estão inválidos");
//
//        Problema problema = Problema.builder()
//                .status(status.value())
//                .dataHora(LocalDateTime.now())
//                .mensagem(exception.getMessage()).build();
//
//
//        return handleExceptionInternal(exception, problema, headers, status, request);
//
//
//    }

}
