// core/services/cliente.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente, ClienteRequest } from '../models/cliente.model';

@Injectable({ providedIn: 'root' })
export class ClienteService {
  private readonly baseUrl = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient) {}

  crear(cliente: ClienteRequest): Observable<Cliente> {
    return this.http.post<Cliente>(this.baseUrl, cliente);
  }

  buscarPorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseUrl}/${id}`);
  }
}