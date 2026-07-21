package com.ciberaccion.hexacredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCredito {

    private static final int MULTIPLO_MAXIMO_INGRESOS = 5;

    private Long id;
    private Cliente cliente;
    private Integer montoSolicitado;
    private Integer plazoMeses;
    private EstadoSolicitud estado;
    private LocalDate fechaSolicitud;

    public boolean cumpleCapacidadPago() {
        return montoSolicitado <= cliente.getIngresosMensuales() * MULTIPLO_MAXIMO_INGRESOS;
    }
}