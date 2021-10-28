package com.umanizales.lists_prog2.controller;

import com.umanizales.lists_prog2.controller.dto.ErrorDTO;
import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaSeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(ListaSeException.class)
    protected ResponseEntity<?> handle(ListaSeException ex){
        String message = ex.getMessage();
        ResponseDTO response = new ResponseDTO(message,null,null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handle(MethodArgumentNotValidException ex) {
        List<ErrorDTO> listErroes = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) ->  {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            listErroes.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldName +" "+errorMessage));
        });
        String message = "Algunos campos son invalidos o faltantes, por favor corrija los errores yvuelva a intentarlo.";
        ResponseDTO response = new ResponseDTO(message,null,listErroes);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
