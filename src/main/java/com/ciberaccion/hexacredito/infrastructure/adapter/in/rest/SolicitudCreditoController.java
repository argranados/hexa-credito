package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest;

import com.ciberaccion.hexacredito.domain.model.Cliente;
import com.ciberaccion.hexacredito.domain.model.SolicitudCredito;
import com.ciberaccion.hexacredito.domain.port.in.ConsultarClienteUseCase;
import com.ciberaccion.hexacredito.domain.port.in.EvaluarSolicitudCreditoUseCase;
import com.ciberaccion.hexacredito.domain.port.in.SolicitarCreditoUseCase;
import com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto.SolicitudCreditoRequest;
import com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto.SolicitudCreditoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudCreditoController {

    private final SolicitarCreditoUseCase solicitarCreditoUseCase;
    private final EvaluarSolicitudCreditoUseCase evaluarSolicitudCreditoUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudCreditoResponse solicitar(@Valid @RequestBody SolicitudCreditoRequest request) {
        Cliente cliente = consultarClienteUseCase.buscarPorId(request.clienteId());

        SolicitudCredito solicitud = new SolicitudCredito(
                null, cliente, request.montoSolicitado(), request.plazoMeses(), null, null
        );

        SolicitudCredito creada = solicitarCreditoUseCase.solicitar(solicitud);
        return SolicitudCreditoRestMapper.toResponse(creada);
    }

    @PostMapping("/{id}/evaluar")
    public SolicitudCreditoResponse evaluar(@PathVariable Long id) {
        SolicitudCredito evaluada = evaluarSolicitudCreditoUseCase.evaluar(id);
        return SolicitudCreditoRestMapper.toResponse(evaluada);
    }
}