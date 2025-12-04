EnviosApp — Sistema de gestión de envíos (enfoque en patrones de diseño)

## Breve descripción

EnviosApp es una aplicación demostrativa desarrollada con Spring Boot y Thymeleaf que modela un dominio de logística: envíos, rutas, cargas y trazabilidad. El propósito del proyecto es servir como ejemplo práctico para aplicar y observar patrones de diseño en un contexto realista (MVC, Observer, Facade, Strategy, Composite, Adapter/Proxy donde aplica).

## Objetivos

- Proveer una interfaz web (Thymeleaf) para operaciones CRUD sobre `Envio`, `Ruta` y `Carga`.
- Exponer una API REST para integración programática.
- Registrar trazabilidad de cambios de estado mediante un sistema de eventos/oyentes.
- Mostrar aplicación de patrones de diseño en componentes concretos del código.

## Estructura del proyecto

- Código Java: `src/main/java/pe/edu/utp/` (controladores, servicios, repositorios, dominio, eventos).
- Plantillas Thymeleaf: `src/main/resources/templates/` (vistas para envíos, rutas, cargas, trazabilidad).
- Estáticos: `src/main/resources/static/` (`css/style.css`, `js/app.js`).
- Build: Maven con wrapper (`mvnw.cmd`).

## Patrones de diseño aplicados (y dónde encontrarlos)

- **Observer (Event Listener)**: cuando cambia el estado de un `Envio` se publica `EnvioStateCambioEvento` y un oyente (`TrazabilidadOyente` o similar) persiste registros de trazabilidad. Esto desacopla la lógica de negocio de la persistencia del historial.
- **Facade**: `EnvioFacade` encapsula flujos de alto nivel (por ejemplo, crear un envío y programar su recogida) para simplificar el uso desde controladores o clientes.
- **Strategy (comportamiento intercambiable)**: la selección o filtrado de rutas puede implementarse con múltiples estrategias de criterio (p. ej., rápida vs económica) en `RutaServicio` / `RutaServicioImpl`.
- **Composite**: `Carga` contiene múltiples `CargaItem` —modelo compuesto que permite operar sobre conjuntos de items como una carga única.
- **Adapter / Proxy**: ejemplos en `adapter` y `proxy` que muestran integración con servicios externos (p. ej., adaptador para GPS externo o proxy de servicios remotos) para aislar dependencias externas.

## Mapeo de archivos relevantes (ejemplos)

- Eventos y oyentes: `src/main/java/.../evento/EnvioStateCambioEvento.java`, `src/main/java/.../escuchador/SeguimientoOyente.java` (o `TrazabilidadOyente`).
- Facade: `src/main/java/.../facade/EnvioFacade.java`.
- Servicios: `src/main/java/.../servicio/EnvioServicio.java` y `impl/EnvioServicioImpl.java`.
- Repositorios (Spring Data JPA): `src/main/java/.../repository/*Repository.java`.
- Vistas: `src/main/resources/templates/layout.html`, `templates/envios/*.html`, `templates/rutas/*.html`.

## Características principales

- CRUD para `Envio`, `Ruta` y `Carga` desde la UI.
- Selección de ruta en el formulario de creación de envío (se persiste `rutaId`).
- Registro automático de trazabilidad al avanzar estado de un envío.
- API REST para envíos, rutas, cargas y trazabilidad: `GET`, `POST`, `PUT`, `DELETE` según controlador correspondiente.
- Búsqueda rápida desde la barra de navegación (por ID o texto en origen/destino).

## DTOs (Data Transfer Objects)

Para separar las entidades JPA del contrato de la API y de las vistas, el proyecto incluye varios DTOs dentro del paquete `pe.edu.utp.dto`.

- **Ubicación:** `src/main/java/pe/edu/utp/dto/`
- **Clases añadidas (ejemplo):** `EnvioDTO`, `EnvioCreateDTO`, `RutaDTO`, `CargaDTO`, `CargaItemDTO`, `TrazabilidadDTO`.
- **Propósito:**

  - `EnvioDTO` sirve para devolver datos de un `Envio` (incluye `id` y `fecha`).
  - `EnvioCreateDTO` se usa para recibir datos de creación (desde formularios o API) sin exponer campos de entidad que no deberían recibirse directamente.
  - Otros DTOs (`RutaDTO`, `CargaDTO`, `CargaItemDTO`, `TrazabilidadDTO`) modelan respuestas o inputs para sus correspondientes entidades.

- **Recomendaciones:**
  - Implementar mapeadores entre entidades y DTOs. Puedes crear mappers manuales (`EnvioMapper.toDto(...)`, `EnvioMapper.fromCreateDto(...)`) o usar **MapStruct** para generación automática.
  - Validar DTOs en los controladores con `@Valid` y anotaciones de Bean Validation (`@NotNull`, `@Size`, etc.).
  - Usar DTOs para los endpoints públicos (REST) y adaptar las vistas Thymeleaf a DTOs si quieres mantener una clara separación entre capa de persistencia y presentación.

Ejemplo de uso rápido en un controlador REST:

```java
// convertir entidad a DTO antes de devolver
Envio envio = envioServicio.findById(id);
EnvioDTO dto = EnvioMapper.toDto(envio);
return ResponseEntity.ok(dto);
```

## Cómo ejecutar (Windows / PowerShell)

1. Abrir PowerShell en la carpeta del proyecto:

```powershell
cd C:\Users\adria\dev\diseno_patrones
.\mvnw.cmd -DskipTests spring-boot:run
```

2. Abrir en el navegador: `http://localhost:8080/` — interfaz Thymeleaf. API REST disponible en `http://localhost:8080/api/`.

## Base de datos (PostgreSQL)

La aplicación espera una base de datos PostgreSQL llamada `fedexDB`. Tienes dos opciones:

- Actualizar `src/main/resources/application.properties` con el usuario/contraseña/URL de la base de datos que prefieras. Asegúrate de que la propiedad `spring.datasource.url` apunte a la base correcta y que `spring.datasource.username` y `spring.datasource.password` se correspondan con un usuario con permisos.
- O crear una instancia PostgreSQL rápida con Docker usando el siguiente comando (ejemplo):

```powershell
docker run --name disenoPatrones -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password1 -e POSTGRES_DB=fedexDB -p 5432:5432 -d postgres
```

Después de crear la base con Docker, ajusta `application.properties` si quieres cambiar las credenciales (por defecto en este ejemplo: usuario `admin`, password `password1`, base `fedexDB`).

Si no existe la base `fedexDB` ni la configuración indicada, la aplicación no podrá arrancar correctamente hasta que la fuente de datos esté accesible.

## Credenciales de demo

Para pruebas rápidas con el sistema de login incluido en la aplicación, usa estas credenciales de demostración:

- Usuario: `admin`
- Contraseña: `password1`

Estas mismas credenciales se usan en el ejemplo de creación de la base de datos Docker mostrado más arriba (usuario `admin`, contraseña `password1`, base `fedexDB`).

## Notas de diseño y recomendaciones

- `rutaId` actualmente se persiste como `Long` dentro de `Envio`. Si necesitas navegación por objeto o consultas JPA más ricas, convierte `rutaId` en una relación `@ManyToOne` hacia `Ruta`.
- Añadir DTOs y validaciones (Spring Validation) para endurecer la API y las peticiones desde formularios.
- Agregar paginación a los endpoints list para evitar devolver colecciones grandes.
- Documentar API con OpenAPI/Swagger para facilitar integraciones.

## Contribuciones

Pull requests y mejoras son bienvenidas. Para cambios grandes es preferible abrir un issue describiendo la propuesta (p. ej., migrar `rutaId` a relación, añadir autenticación, etc.).

## Licencia

Repositorio de ejemplo .
