// core/models/solicitud-credito.model.ts
import { Cliente } from './cliente.model';

export type EstadoSolicitud = 'PENDIENTE' | 'APROBADA' | 'RECHAZADA';

export interface SolicitudCredito {
  id: number;
  cliente: Cliente;
  montoSolicitado: number;
  plazoMeses: number;
  estado: EstadoSolicitud;
  fechaSolicitud: string;
}

export interface SolicitudCreditoRequest {
  clienteId: number;
  montoSolicitado: number;
  plazoMeses: number;
}