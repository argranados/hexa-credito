package com.ciberaccion.hexacredito.domain.exception;

public class SolicitudCreditoNoEncontradaException extends RuntimeException {
    public SolicitudCreditoNoEncontradaException(Long id) {
        super("No se encontró la solicitud de crédito con id: " + id);
    }
}
