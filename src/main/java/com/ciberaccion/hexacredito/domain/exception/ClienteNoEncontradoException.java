package com.ciberaccion.hexacredito.domain.exception;

public class ClienteNoEncontradoException extends RuntimeException{
    public ClienteNoEncontradoException(Long id) {
        super("No se encontró la solicitud de crédito con id: " + id);
    }
}
