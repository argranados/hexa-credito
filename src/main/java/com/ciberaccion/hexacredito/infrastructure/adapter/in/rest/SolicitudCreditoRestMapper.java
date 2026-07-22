package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;
import com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto.SolicitudCreditoResponse;

public class SolicitudCreditoRestMapper {

    public static SolicitudCreditoResponse toResponse(SolicitudCredito solicitud) {
        return new SolicitudCreditoResponse(
                solicitud.getId(),
                ClienteRestMapper.toResponse(solicitud.getCliente()),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoMeses(),
                solicitud.getEstado(),
                solicitud.getFechaSolicitud()
        );
    }
}