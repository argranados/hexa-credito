import { Component, ChangeDetectorRef, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { SolicitudCreditoService } from '../../../core/services/solicitud-credito.service';

@Component({
  selector: 'app-solicitud-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './solicitud-form.html',
  styleUrl: './solicitud-form.css'
})
export class SolicitudForm {
  private fb = inject(FormBuilder);
  private solicitudCreditoService = inject(SolicitudCreditoService);
  private router = inject(Router);
  private cdr = inject(ChangeDetectorRef);

  form = this.fb.group({
    clienteId: [null as number | null, [Validators.required, Validators.min(1)]],
    montoSolicitado: [null as number | null, [Validators.required, Validators.min(1)]],
    plazoMeses: [null as number | null, [Validators.required, Validators.min(1)]]
  });

  enviando = false;
  errorMessage: string | null = null;

  enviar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.enviando = true;
    this.errorMessage = null;

    const { clienteId, montoSolicitado, plazoMeses } = this.form.getRawValue();

    this.solicitudCreditoService.solicitar({
      clienteId: clienteId!,
      montoSolicitado: montoSolicitado!,
      plazoMeses: plazoMeses!
    }).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (err: HttpErrorResponse) => {
        this.enviando = false;
        this.errorMessage = err.error?.mensaje ?? 'Ocurrió un error al enviar la solicitud.';
        this.cdr.markForCheck();
      }
    });
  }
}