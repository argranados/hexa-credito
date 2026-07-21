package com.ciberaccion.hexacredito.application.usecase;

import org.springframework.stereotype.Service;

import com.ciberaccion.hexacredito.domain.model.Cliente;
import com.ciberaccion.hexacredito.domain.port.in.CrearClienteUseCase;
import com.ciberaccion.hexacredito.domain.port.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrearClienteUseCaseImpl implements CrearClienteUseCase{

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente crear(Cliente cliente) {        
        return clienteRepositoryPort.guardar(cliente);
    }

}
