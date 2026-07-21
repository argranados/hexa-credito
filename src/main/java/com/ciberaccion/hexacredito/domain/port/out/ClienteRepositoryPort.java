package com.ciberaccion.hexacredito.domain.port.out;

import java.util.Optional;

import com.ciberaccion.hexacredito.domain.model.Cliente;

public interface ClienteRepositoryPort {
    Optional<Cliente> buscarPorId(Long id);
    Cliente guardar(Cliente cliente);
}
