package com.ciberaccion.hexacredito.domain.port.in;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;

public interface EvaluarSolicitudCreditoUseCase {
    SolicitudCredito evaluar(Long solicitudId);
}
