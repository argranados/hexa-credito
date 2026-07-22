package com.ciberaccion.hexacredito.infrastructure.adapter.out.persistence;

import com.ciberaccion.hexacredito.domain.model.EstadoSolicitud;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "solicitudes_credito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    private Integer montoSolicitado;
    private Integer plazoMeses;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private LocalDate fechaSolicitud;
}