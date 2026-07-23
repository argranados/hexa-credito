import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudCreditoService } from '../../../core/services/solicitud-credito.service';
import { SolicitudCredito } from '../../../core/models/solicitud-credito.model';

@Component({
  selector: 'app-solicitud-list',
  imports: [RouterLink],
  templateUrl: './solicitud-list.html',
  styleUrl: './solicitud-list.css'
})
export class SolicitudList implements OnInit {
  solicitudes: SolicitudCredito[] = [];
  cargando = true;
  error = false;

  constructor(
    private solicitudCreditoService: SolicitudCreditoService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.solicitudCreditoService.listarTodas().subscribe({
      next: (data) => {
        this.solicitudes = data;
        this.cargando = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.error = true;
        this.cargando = false;
        this.cdr.markForCheck();
      }
    });
  }

  evaluar(id: number): void {
    this.solicitudCreditoService.evaluar(id).subscribe({
      next: (actualizada) => {
        this.solicitudes = this.solicitudes.map(s => (s.id === actualizada.id ? actualizada : s));
        this.cdr.markForCheck();
      }
    });
  }
}