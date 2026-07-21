package com.ciberaccion.hexacredito.domain.port.in;

import com.ciberaccion.hexacredito.domain.model.Cliente;

public interface CrearClienteUseCase {
    Cliente crear(Cliente cliente);
}
