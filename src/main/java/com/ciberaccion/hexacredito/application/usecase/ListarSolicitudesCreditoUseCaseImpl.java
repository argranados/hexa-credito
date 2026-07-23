package com.ciberaccion.hexacredito.application.usecase;

import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;
import com.ciberaccion.hexacredito.domain.port.in.ListarSolicitudesCreditoUseCase;
import com.ciberaccion.hexacredito.domain.port.out.SolicitudCreditoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarSolicitudesCreditoUseCaseImpl implements ListarSolicitudesCreditoUseCase {

    private final SolicitudCreditoRepositoryPort solicitudCreditoRepositoryPort;

    @Override
    public List<SolicitudCredito> listarTodas() {
        return solicitudCreditoRepositoryPort.listarTodas();
    }
}