package com.ryudevcode.usuarios.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.ObjectName;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String , Object>> manejarNoEncontrado(
            ResourceNotFoundException exception){
        Map<String , Object> respuesta = new HashMap<>();
        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("Estado", 404);
        respuesta.put("error", "Not Found");
        respuesta.put("mesaje", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity <Map<String, Object>> manejarDuplicado(
             DuplicateResourceException exception){

        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("fecha",LocalDateTime.now());
        respuesta.put("Estado", 409);
        respuesta.put("Error","Conflict");
        respuesta.put("mensaje",exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(
            MethodArgumentNotValidException exception){
        Map<String , String> errores = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errores.put(
                         error.getField(),
                        error.getDefaultMessage()
                        )

                );

        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("fecha",LocalDateTime.now());
        respuesta.put("Estado", 400);
        respuesta.put("Error","Bad Request");
        respuesta.put("mensaje",errores);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String , Object>> manejarIntegridad(
         DataIntegrityViolationException exception ){


        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("fecha",LocalDateTime.now());
        respuesta.put("Estado", 409);
        respuesta.put("Error","Conflict");
        respuesta.put(
                "mensaje",
                "No se pudo completar la operacion por una restriccion de la base  de datos");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);

    }






}
