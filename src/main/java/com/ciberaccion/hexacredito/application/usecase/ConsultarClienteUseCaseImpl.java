package com.ciberaccion.hexacredito.application.usecase;

import org.springframework.stereotype.Service;

import com.ciberaccion.hexacredito.domain.exception.ClienteNoEncontradoException;
import com.ciberaccion.hexacredito.domain.model.Cliente;
import com.ciberaccion.hexacredito.domain.port.in.ConsultarClienteUseCase;
import com.ciberaccion.hexacredito.domain.port.out.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepositoryPort.buscarPorId(id).
        orElseThrow(() -> new ClienteNoEncontradoException (id));
    }

}
