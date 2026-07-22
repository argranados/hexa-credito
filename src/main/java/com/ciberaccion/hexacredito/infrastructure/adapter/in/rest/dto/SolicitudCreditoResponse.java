package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto;

import com.ciberaccion.hexacredito.domain.model.EstadoSolicitud;

import java.time.LocalDate;

public record SolicitudCreditoResponse(
        Long id,
        ClienteResponse cliente,
        Integer montoSolicitado,
        Integer plazoMeses,
        EstadoSolicitud estado,
        LocalDate fechaSolicitud
) {}