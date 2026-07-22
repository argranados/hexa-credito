package com.ciberaccion.hexacredito.infrastructure.adapter.in.rest;

import com.ciberaccion.hexacredito.domain.model.Cliente;
import com.ciberaccion.hexacredito.domain.port.in.ConsultarClienteUseCase;
import com.ciberaccion.hexacredito.domain.port.in.CrearClienteUseCase;
import com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto.ClienteRequest;
import com.ciberaccion.hexacredito.infrastructure.adapter.in.rest.dto.ClienteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final CrearClienteUseCase crearClienteUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse crear(@Valid @RequestBody ClienteRequest request) {
        Cliente cliente = ClienteRestMapper.toDomain(request);
        Cliente creado = crearClienteUseCase.crear(cliente);
        return ClienteRestMapper.toResponse(creado);
    }

    @GetMapping("/{id}")
    public ClienteResponse consultar(@PathVariable Long id) {
        Cliente cliente = consultarClienteUseCase.buscarPorId(id);
        return ClienteRestMapper.toResponse(cliente);
    }
}