package com.ciberaccion.hexacredito.infrastructure.adapter.out.persistence;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;
import com.ciberaccion.hexacredito.domain.port.out.SolicitudCreditoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SolicitudCreditoRepositoryAdapter implements SolicitudCreditoRepositoryPort {

    private final SolicitudCreditoJpaRepository solicitudCreditoJpaRepository;

    @Override
    public SolicitudCredito guardar(SolicitudCredito solicitud) {
        SolicitudCreditoEntity entity = SolicitudCreditoMapper.toEntity(solicitud);
        SolicitudCreditoEntity guardado = solicitudCreditoJpaRepository.save(entity);
        return SolicitudCreditoMapper.toDomain(guardado);
    }

    @Override
    public Optional<SolicitudCredito> buscarPorId(Long id) {
        return solicitudCreditoJpaRepository.findById(id)
                .map(SolicitudCreditoMapper::toDomain);
    }

    @Override
    public List<SolicitudCredito> listarTodas() {
        return solicitudCreditoJpaRepository.findAll().stream()
                .map(SolicitudCreditoMapper::toDomain)
                .toList();
    }
}