package com.ciberaccion.hexacredito.domain.port.out;

import com.ciberaccion.hexacredito.domain.model.Cliente;

public interface ClienteRepositoryPort {
    Cliente buscarPorId(Long id);
    Cliente guardar(Cliente cliente);
}
