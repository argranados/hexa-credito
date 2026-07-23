// app.routes.ts
import { Routes } from '@angular/router';
import { SolicitudList } from './features/solicitudes/solicitud-list/solicitud-list';

export const routes: Routes = [
  { path: '', component: SolicitudList },
];