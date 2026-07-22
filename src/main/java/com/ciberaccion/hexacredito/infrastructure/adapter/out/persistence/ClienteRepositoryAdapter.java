package com.ciberaccion.hexacredito.infrastructure.adapter.out.persistence;

import com.ciberaccion.hexacredito.domain.model.Cliente;
import com.ciberaccion.hexacredito.domain.port.out.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteJpaRepository.findById(id)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        ClienteEntity entity = ClienteMapper.toEntity(cliente);
        ClienteEntity guardado = clienteJpaRepository.save(entity);
        return ClienteMapper.toDomain(guardado);
    }
}