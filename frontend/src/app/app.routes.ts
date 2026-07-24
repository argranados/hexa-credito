import { Routes } from '@angular/router';
import { SolicitudList } from './features/solicitudes/solicitud-list/solicitud-list';
import { SolicitudForm } from './features/solicitudes/solicitud-form/solicitud-form';

export const routes: Routes = [
  { path: '', component: SolicitudList },
  { path: 'solicitudes/nueva', component: SolicitudForm },
];