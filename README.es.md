# hexa-credito

Sistema de gestión de solicitudes de crédito construido como proyecto de práctica para reforzar **arquitectura hexagonal** (backend) y **Angular** (frontend) — ambos listados como conocimientos deseables en un proceso de entrevista.

No es un proyecto de producción: las decisiones de alcance (versiones de dependencias, reglas de negocio, cobertura de endpoints) están tomadas para maximizar aprendizaje en un ejercicio de dos días, no para un caso de uso real.

## Stack técnico

**Backend**
- Java 17
- Spring Boot 3.5.13 (Web, Data JPA, Validation, Actuator)
- Lombok
- H2 (base de datos en memoria)
- Maven

**Frontend**
- Angular 22 (standalone components, zoneless change detection)
- RxJS clásico (Observables + `subscribe()`, sin Signals)
- Reactive Forms
- Sin Node/npm instalado localmente — todo corre en Docker

## Arquitectura

El backend sigue **arquitectura hexagonal (puertos y adaptadores)**: el dominio no depende de Spring, JPA ni HTTP.

```
com.ciberaccion.hexacredito/
├── domain/
│   ├── model/              Cliente, SolicitudCredito, EstadoSolicitud — POJOs puros
│   ├── port/in/             Casos de uso (contratos de entrada)
│   ├── port/out/            Repositorios y colaboradores externos (contratos de salida)
│   └── exception/           Excepciones de negocio
├── application/
│   └── usecase/             Implementación de los casos de uso, orquesta dominio + puertos
└── infrastructure/
    ├── adapter/in/rest/     Controllers, DTOs, mapper REST, manejo global de errores
    ├── adapter/out/persistence/  Entidades JPA, repositorios Spring Data, mapper, adaptador
    ├── adapter/out/external/     Mock del Buró de Crédito
    └── config/               CORS y demás configuración de infraestructura
```

**Regla de negocio principal:** una solicitud se aprueba si el monto solicitado es ≤ 5× los ingresos mensuales del cliente **y** el cliente no aparece en la lista negra (simulada) del Buró de Crédito.

El frontend Angular sigue una separación por capas más ligera:

```
frontend/src/app/
├── core/
│   ├── models/               Interfaces TypeScript, espejo de los DTOs del backend
│   └── services/              Clases HTTP (HttpClient) hacia la API
└── features/
    └── solicitudes/
        ├── solicitud-list/    Tabla de solicitudes + acción "Evaluar"
        └── solicitud-form/    Formulario para crear una nueva solicitud
```

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/clientes` | Crea un cliente |
| `GET` | `/api/clientes/{id}` | Consulta un cliente por id |
| `POST` | `/api/solicitudes` | Solicita un crédito (queda en estado `PENDIENTE`) |
| `GET` | `/api/solicitudes` | Lista todas las solicitudes |
| `POST` | `/api/solicitudes/{id}/evaluar` | Evalúa una solicitud pendiente (`APROBADA` / `RECHAZADA`) |

Colección de pruebas lista para usar: [`hexa-credito.http`](./hexa-credito.http) (requiere la extensión **REST Client** de VS Code).

## Cómo correr el backend

```bash
./mvnw spring-boot:run
```

Levanta en `http://localhost:8080`. Usa H2 en memoria — los datos se pierden al reiniciar.

## Cómo correr el frontend

No requiere instalar Node ni Angular CLI localmente — corre en un contenedor con la carpeta montada como volumen (hot-reload incluido):

```bash
docker compose up
```

Levanta en `http://localhost:4200`. El backend debe estar corriendo antes (el frontend depende de la API para todo).

> En Git Bash (Windows), si `docker run`/`docker compose` fallan con un error de rutas, prefija el comando con `MSYS_NO_PATHCONV=1`.

## Decisiones de alcance (a propósito, no descuidos)

- **Java 17 y Spring Boot 3.5.13**, no las versiones más recientes — preferencia explícita para evitar sorpresas de compatibilidad durante la práctica.
- **Sin CRUD completo de `Cliente`** — solo crear y consultar; no hay actualizar/eliminar.
- **`ConsultaBuroPort` está mockeado en memoria** (dos documentos hardcodeados como "lista negra"), no integra un servicio real.
- **`FetchType.EAGER`** en la relación `SolicitudCredito → Cliente`, elegido conscientemente sobre `LAZY` para evitar manejo explícito de transacciones en el mapper.
- **RxJS clásico en el frontend**, no Signals — decisión deliberada pensando en que el código de producción real más probablemente use el patrón "tradicional".
- **Sin tests automatizados** — el ejercicio se validó manualmente vía el archivo `.http`.

## Autor

Proyecto de práctica personal, construido guiado paso a paso concepto por concepto.
