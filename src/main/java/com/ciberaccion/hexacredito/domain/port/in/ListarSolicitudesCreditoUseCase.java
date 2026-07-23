package com.ciberaccion.hexacredito.domain.port.in;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;

import java.util.List;

public interface ListarSolicitudesCreditoUseCase {
    List<SolicitudCredito> listarTodas();
}