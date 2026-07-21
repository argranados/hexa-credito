package com.ciberaccion.hexacredito.domain.port.out;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;

public interface SolicitudCreditoRepositoryPort {
    SolicitudCredito guardar(SolicitudCredito solicitud);
    SolicitudCredito buscarPorId(Long id);
}
