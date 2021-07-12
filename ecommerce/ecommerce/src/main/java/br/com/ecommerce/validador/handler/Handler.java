package br.com.ecommerce.validador.handler;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValid(MethodArgumentNotValidException e){
        List<Errors> erros=e.getFieldErrors().stream().map(Errors::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> BindException(BindException e){
        List<Errors>erros=e.getFieldErrors().stream().map(Errors::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

}