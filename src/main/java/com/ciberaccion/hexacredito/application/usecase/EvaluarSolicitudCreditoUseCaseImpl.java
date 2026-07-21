package com.ciberaccion.hexacredito.application.usecase;

import com.ciberaccion.hexacredito.domain.exception.SolicitudCreditoNoEncontradaException;
import com.ciberaccion.hexacredito.domain.model.EstadoSolicitud;
import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;
import com.ciberaccion.hexacredito.domain.port.in.EvaluarSolicitudCreditoUseCase;
import com.ciberaccion.hexacredito.domain.port.out.ConsultaBuroPort;
import com.ciberaccion.hexacredito.domain.port.out.SolicitudCreditoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluarSolicitudCreditoUseCaseImpl implements EvaluarSolicitudCreditoUseCase {

    private final SolicitudCreditoRepositoryPort solicitudCreditoRepositoryPort;
    private final ConsultaBuroPort consultaBuroPort;

    @Override
    public SolicitudCredito evaluar(Long solicitudId) {
        SolicitudCredito solicitud = solicitudCreditoRepositoryPort.buscarPorId(solicitudId)
                .orElseThrow(() -> new SolicitudCreditoNoEncontradaException(solicitudId));

        boolean enListaNegra = consultaBuroPort.estaEnListaNegra(solicitud.getCliente().getDocumentoIdentidad());
        boolean cumpleCapacidad = solicitud.cumpleCapacidadPago();

        EstadoSolicitud nuevoEstado = (!enListaNegra && cumpleCapacidad)
                ? EstadoSolicitud.APROBADA
                : EstadoSolicitud.RECHAZADA;

        SolicitudCredito solicitudEvaluada = new SolicitudCredito(
                solicitud.getId(),
                solicitud.getCliente(),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoMeses(),
                nuevoEstado,
                solicitud.getFechaSolicitud());

        return solicitudCreditoRepositoryPort.guardar(solicitudEvaluada);
    }
}