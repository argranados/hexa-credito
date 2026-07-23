// core/services/solicitud-credito.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SolicitudCredito, SolicitudCreditoRequest } from '../models/solicitud-credito.model';

@Injectable({ providedIn: 'root' })
export class SolicitudCreditoService {
  private readonly baseUrl = 'http://localhost:8080/api/solicitudes';

  constructor(private http: HttpClient) {}

  solicitar(solicitud: SolicitudCreditoRequest): Observable<SolicitudCredito> {
    return this.http.post<SolicitudCredito>(this.baseUrl, solicitud);
  }

  evaluar(id: number): Observable<SolicitudCredito> {
    return this.http.post<SolicitudCredito>(`${this.baseUrl}/${id}/evaluar`, {});
  }
}