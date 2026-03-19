# Arquitectura y modelo de datos

## Arquitectura general

El proyecto sigue una arquitectura por capas:

- `controllers`: expone endpoints REST y respuestas HTTP
- `service`: aplica reglas de negocio
- `repositories`: acceso a base de datos con Spring Data JPA
- `model`: entidades JPA
- `dtos`: contratos de entrada y salida
- `security`: autenticacion JWT y proteccion de rutas
- `exceptions`: manejo centralizado de errores

## Flujo de una peticion

1. El cliente hace una solicitud HTTP
2. `JwtFilter` revisa si existe header `Authorization`
3. Si el token es valido, se registra autenticacion en `SecurityContext`
4. El controller delega al service
5. El service aplica reglas de negocio
6. El repository consulta o persiste datos
7. La respuesta se transforma a DTO

## Modelo de datos

## Tabla `product`

| Campo | Tipo | Descripcion |
| --- | --- | --- |
| `id` | `INT` | identificador del producto |
| `name` | `VARCHAR(250)` | nombre del producto |
| `description` | `TEXT` | descripcion opcional |
| `unitary_price` | `DECIMAL(10,2)` | precio unitario |
| `stock` | `INT` | stock disponible |
| `created_at` | `TIMESTAMP` | fecha de creacion |
| `updated_at` | `TIMESTAMP` | fecha de ultima actualizacion |

## Tabla `sale`

| Campo | Tipo | Descripcion |
| --- | --- | --- |
| `id` | `INT` | identificador de la venta |
| `customer_name` | `VARCHAR(250)` | nombre del cliente |
| `description` | `TEXT` | descripcion de la venta |
| `created_at` | `TIMESTAMP` | fecha de creacion |
| `updated_at` | `TIMESTAMP` | fecha de actualizacion |

## Tabla `sale_detail`

| Campo | Tipo | Descripcion |
| --- | --- | --- |
| `id` | `INT` | identificador del detalle |
| `sale_id` | `INT` | referencia a la venta |
| `product_id` | `INT` | referencia al producto |
| `quantity` | `INT` | cantidad vendida |
| `unit_price` | `DECIMAL(10,2)` | precio congelado al momento de la venta |
| `created_at` | `TIMESTAMP` | fecha de creacion |
| `updated_at` | `TIMESTAMP` | fecha de actualizacion |

## Relaciones

- `Sale` 1 a N `SaleDetail`
- `Product` 1 a N `SaleDetail`
- cada `SaleDetail` pertenece a una sola venta
- cada `SaleDetail` referencia un solo producto

## Reglas de negocio clave

## Productos

- no se permiten nombres vacios
- el precio no puede ser negativo
- el stock no puede ser negativo

## Ventas

- una venta debe tener al menos un detalle
- cada detalle debe indicar producto y cantidad
- la cantidad debe ser como minimo `1`
- no se puede vender mas stock del disponible
- el total de la venta se calcula sumando subtotales

## Actualizacion de venta

- se restaura stock de los detalles actuales
- se vacian los detalles anteriores
- se crean nuevos detalles
- se vuelve a descontar stock

## Eliminacion de venta

- antes de borrar, se restaura el stock de todos sus detalles

## Seguridad

La seguridad es stateless con JWT:

- login publico en `/auth/login`
- Swagger publico para documentacion
- demas endpoints autenticados

## Componentes de seguridad

- `SecurityConfig`: define rutas publicas, CORS, CSRF desactivado y filtro JWT
- `JwtFilter`: extrae y valida el token Bearer
- `JwtService`: genera y valida tokens

## Manejo de errores

`GlobalExceptionHandler` centraliza:

- `ResourceNotFoundException` -> `404`
- `BadRequestException` -> `400`
- `MethodArgumentNotValidException` -> `400` con detalle de campos
- `Exception` -> `500`

## Consideraciones actuales

- existe un endpoint Thymeleaf en `/`
- el login actual usa credenciales hardcodeadas
- el secreto JWT esta embebido en codigo
- la configuracion de base de datos esta fija en `application.properties`

