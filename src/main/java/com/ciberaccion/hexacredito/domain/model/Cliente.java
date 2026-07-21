package com.ciberaccion.hexacredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long id;
    private String nombre;
    private String documentoIdentidad;
    private Integer ingresosMensuales;
}