package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto;

public record ClienteResponse(
        Long id,
        String nombre,
        String documentoIdentidad,
        Integer ingresosMensuales
) {}