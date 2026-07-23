package com.ciberaccion.hexacredito.domain.port.out;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;

import java.util.List;
import java.util.Optional;

public interface SolicitudCreditoRepositoryPort {
    SolicitudCredito guardar(SolicitudCredito solicitud);
    Optional<SolicitudCredito> buscarPorId(Long id);
    List<SolicitudCredito> listarTodas();
}
