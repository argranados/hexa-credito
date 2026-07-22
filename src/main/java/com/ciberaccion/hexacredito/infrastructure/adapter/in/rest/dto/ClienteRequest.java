package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El documento de identidad es obligatorio")
        String documentoIdentidad,

        @NotNull(message = "Los ingresos mensuales son obligatorios")
        @Min(value = 1, message = "Los ingresos mensuales deben ser mayores a 0")
        Integer ingresosMensuales
) {}