package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SolicitudCreditoRequest(
        @NotNull(message = "El id del cliente es obligatorio")
        Long clienteId,

        @NotNull(message = "El monto solicitado es obligatorio")
        @Min(value = 1, message = "El monto solicitado debe ser mayor a 0")
        Integer montoSolicitado,

        @NotNull(message = "El plazo en meses es obligatorio")
        @Min(value = 1, message = "El plazo debe ser de al menos 1 mes")
        Integer plazoMeses
) {}