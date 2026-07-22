package com.ciberaccion.hexacredito.infrastructure.adapter.out.external;

import com.ciberaccion.hexacredito.domain.port.out.ConsultaBuroPort;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConsultaBuroAdapter implements ConsultaBuroPort {

    private static final Set<String> LISTA_NEGRA = Set.of(
            "00000000",
            "11111111"
    );

    @Override
    public boolean estaEnListaNegra(String documentoIdentidad) {
        return LISTA_NEGRA.contains(documentoIdentidad);
    }
}