// core/models/cliente.model.ts
export interface Cliente {
  id: number;
  nombre: string;
  documentoIdentidad: string;
  ingresosMensuales: number;
}

export interface ClienteRequest {
  nombre: string;
  documentoIdentidad: string;
  ingresosMensuales: number;
}