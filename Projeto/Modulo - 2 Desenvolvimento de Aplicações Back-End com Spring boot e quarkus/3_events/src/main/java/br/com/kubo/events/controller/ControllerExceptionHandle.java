package br.com.kubo.events.controller;

import br.com.kubo.events.dto.ErroDTO;
import br.com.kubo.events.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErroDTO> handleNotFoundException(NotFoundException ex){
        return ResponseEntity.status(404).body(new ErroDTO(ex.getMessage()));
    }
}
