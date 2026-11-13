package com.ryudevcode.usuarios.exception;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String mensaje){
        super(mensaje);
    }
}
