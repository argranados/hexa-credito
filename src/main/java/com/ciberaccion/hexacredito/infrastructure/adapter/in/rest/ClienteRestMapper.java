package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest;

import com.ciberaccion.hexacredito.domain.model.Cliente;
import com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto.ClienteRequest;
import com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto.ClienteResponse;

public class ClienteRestMapper {

    public static Cliente toDomain(ClienteRequest request) {
        return new Cliente(null, request.nombre(), request.documentoIdentidad(), request.ingresosMensuales());
    }

    public static ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getDocumentoIdentidad(), cliente.getIngresosMensuales());
    }
}