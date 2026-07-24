# hexa-credito

A credit-request management system (submit → evaluate → approve/reject) built to demonstrate **hexagonal architecture** end-to-end — from a framework-agnostic domain model in Spring Boot to a zoneless Angular 22 frontend consuming it.

> Built as a guided learning exercise. I made every architectural decision and wrote all the code myself, working with Claude (Anthropic) as a technical mentor throughout — explaining concepts, reviewing my code, catching bugs, and pushing back on design choices along the way.

This is a scoped practice project, not a production system. The scope decisions below are deliberate, not accidental — see [What's next](#whats-next) for what's intentionally left out.

## Technical decisions (and why)

**Hexagonal architecture over classic layered/MVC.** The domain (`SolicitudCredito`, `Cliente`, the approval rule) has zero dependency on Spring, JPA, or HTTP — it's plain Java behind interfaces (ports). This isn't architecture for its own sake: it's what let me swap the credit bureau check from a live dependency to an in-memory mock without touching a single line of business logic, because the use case only knows about `ConsultaBuroPort`, never the implementation behind it.

**Two distinct flavors of outbound port.** Most examples of hexagonal only show persistence ports. I split `SolicitudCreditoRepositoryPort` (persistence) from `ConsultaBuroPort` (external system call) as separate abstractions, because they're conceptually different dependencies with different failure modes — collapsing them into one "repository" interface would have hidden that distinction.

**Rich domain model, not anemic.** `SolicitudCredito.cumpleCapacidadPago()` lives inside the domain entity because it only needs data the entity already owns. Logic that needs an external port (the blacklist check) lives one layer up, in the use case — the domain never depends on infrastructure, even indirectly.

**DTOs at the REST boundary, domain objects nowhere near it.** Request/response contracts are separate classes from the domain model, specifically so the API contract and the domain can evolve independently — and so Bean Validation annotations live in the DTO layer, not polluting the domain.

**`Optional<T>` at every port boundary, never `null`.** Combined with domain-level exceptions (`ClienteNoEncontradoException`) translated to HTTP status codes at the very edge (`@RestControllerAdvice`), not inside the domain. "Not found" is a business concept; "404" is a transport detail — they're deliberately kept in different layers.

**`FetchType.EAGER` chosen over `LAZY`, consciously.** The mapper converts `Entity → Domain` after Spring Data's transaction has already closed, so `LAZY` without explicit transaction management would throw `LazyInitializationException`. I know the tradeoff (N+1 risk at scale) and the correct fix (`LAZY` + an explicit transaction boundary around the query) — I scoped `EAGER` deliberately for this size of dataset rather than over-engineering it.

**Classic RxJS over Signals on the frontend.** Angular 22 pushes Signals as the modern default, but I chose plain `Observable` + `subscribe()` because production Angular codebases are more likely to still be RxJS-based. That choice has a real cost in zoneless Angular (v21+): without Zone.js, mutating component state inside a `subscribe()` callback doesn't trigger a re-render on its own. I hit this as a real bug, diagnosed it, and fixed it with an explicit `ChangeDetectorRef.markForCheck()` — rather than sidestepping the problem by switching to Signals.

**Interface segregation over CRUD-shaped ports.** Instead of one fat `ClienteUseCase` with four methods, there's `CrearClienteUseCase` and `ConsultarClienteUseCase` — each inbound port represents one business intent, not a generic data operation.

## Stack

**Backend** — Java 17 · Spring Boot 3.5 · Spring Data JPA · H2 · Bean Validation · Lombok
**Frontend** — Angular 22 (standalone, zoneless) · RxJS · Reactive Forms
**Infra** — Docker (frontend runs fully containerized, no local Node/npm required)

## Architecture at a glance

```
domain/           → framework-agnostic business model + ports (interfaces only)
application/      → use cases: orchestrate domain + ports, no infrastructure knowledge
infrastructure/   → REST controllers, JPA adapters, external-service adapter, config
```

Dependencies point inward only: `infrastructure → application → domain`. The domain never imports from the other two.

## Running locally

**Backend**
```bash
./mvnw spring-boot:run
```
Runs on `localhost:8080` with an in-memory H2 database.

**Frontend** (no local Angular/Node install needed)
```bash
docker compose up
```
Runs on `localhost:4200`, hot-reload enabled via a mounted volume. Requires the backend running first.

A ready-to-use `.http` request collection (`hexa-credito.http`) is included for exercising every endpoint.

## What's next

Everything here is a scoping choice made for a focused exercise, not a gap I didn't notice:

- **Automated tests** — validated manually via the `.http` collection during development; the natural next step is unit tests for use cases (mocked ports), `@DataJpaTest` for the persistence adapters, and `@WebMvcTest` for the controllers.
- **Real credit bureau integration** — `ConsultaBuroPort` is currently backed by an in-memory mock adapter. Swapping it for a real HTTP client is exactly the kind of change hexagonal is meant to make cheap: zero changes to the use case that consumes it.
- **Full `Cliente` CRUD** — only create + read exist today; update/delete were out of scope for the exercise.
- **Pagination** — `listarTodas()` currently loads the full table; fine at this scale, would move to `Pageable` before this saw real volume.
- **Signals migration on the frontend** — RxJS was the deliberate choice here (see above), but exploring a Signals-based state layer is a natural follow-up now that the RxJS + zoneless interaction is well understood.
