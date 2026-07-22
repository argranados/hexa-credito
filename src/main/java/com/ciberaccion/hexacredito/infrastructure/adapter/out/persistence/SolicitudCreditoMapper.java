package com.ciberaccion.hexacredito.infrastructure.adapter.out.persistence;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;

public class SolicitudCreditoMapper {

    public static SolicitudCredito toDomain(SolicitudCreditoEntity entity) {
        return new SolicitudCredito(
                entity.getId(),
                ClienteMapper.toDomain(entity.getCliente()),
                entity.getMontoSolicitado(),
                entity.getPlazoMeses(),
                entity.getEstado(),
                entity.getFechaSolicitud());
    }

    public static SolicitudCreditoEntity toEntity(SolicitudCredito solicitud) {
        return new SolicitudCreditoEntity(
                solicitud.getId(),
                ClienteMapper.toEntity(solicitud.getCliente()),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoMeses(),
                solicitud.getEstado(),
                solicitud.getFechaSolicitud());
    }
}
