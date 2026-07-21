package com.ciberaccion.hexacredito.application.usecase;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.ciberaccion.hexacredito.domain.model.EstadoSolicitud;
import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;
import com.ciberaccion.hexacredito.domain.port.in.SolicitarCreditoUseCase;
import com.ciberaccion.hexacredito.domain.port.out.SolicitudCreditoRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolicitarCreditoUseCaseImpl implements SolicitarCreditoUseCase {

    private final SolicitudCreditoRepositoryPort solicitudCreditoRepositoryPort;

    @Override
    public SolicitudCredito solicitar(SolicitudCredito solicitud) {
        SolicitudCredito nuevaSolicitud = new SolicitudCredito(
                null,
                solicitud.getCliente(),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoMeses(),
                EstadoSolicitud.PENDIENTE,
                LocalDate.now()
        );
        return solicitudCreditoRepositoryPort.guardar(nuevaSolicitud);
    }

}
