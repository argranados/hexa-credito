package com.ciberaccion.hexacredito.infrastructure.adapter.out.persistence;

import com.ciberaccion.hexacredito.domain.model.Cliente;

public class ClienteMapper {

    public static Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNombre(),
                entity.getDocumentoIdentidad(),
                entity.getIngresosMensuales());
    }

    public static ClienteEntity toEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDocumentoIdentidad(),
                cliente.getIngresosMensuales());
    }
}
