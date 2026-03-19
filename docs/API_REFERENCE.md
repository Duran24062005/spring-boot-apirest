# API Reference

## Informacion general

- Base URL local: `http://localhost:8080`
- Formato principal: `application/json`
- Autenticacion: `Bearer JWT`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Seguridad

### Endpoints publicos

- `POST /auth/login`
- `OPTIONS /**`
- `/swagger-ui/**`
- `/v3/api-docs`
- `/v3/api-docs/swagger-config`

### Endpoints protegidos

Todos los demas endpoints requieren header:

```http
Authorization: Bearer <token>
```

## Estructura de errores

Los errores de negocio y validacion usan un payload basado en `ApiErrorResponse`.

### Error base

```json
{
  "timestamp": "2026-03-18T21:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Venta no encontrada con id: 99",
  "path": "/api/sales/99",
  "validations": null
}
```

### Error de validacion

```json
{
  "timestamp": "2026-03-18T21:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Error de validacion",
  "path": "/api/products",
  "validations": {
    "name": "El nombre es obligatorio",
    "stock": "El stock no puede ser negativo"
  }
}
```

## Esquemas de datos

### LoginRequest

```json
{
  "username": "admin019",
  "password": "admin123!"
}
```

### ProductRequestDto

```json
{
  "name": "Laptop Lenovo",
  "description": "Laptop 16GB RAM",
  "unitaryPrice": 3200.00,
  "stock": 10
}
```

### ProductResponseDto

```json
{
  "id": 1,
  "name": "Laptop Lenovo",
  "description": "Laptop 16GB RAM",
  "unitaryPrice": 3200.00,
  "stock": 10,
  "createdAt": "2026-03-18T20:30:00",
  "updatedAt": "2026-03-18T20:30:00"
}
```

### SaleDetailRequestDto

```json
{
  "productId": 1,
  "quantity": 2
}
```

### SaleRequestDto

```json
{
  "customerName": "Juan Perez",
  "description": "Compra de equipos",
  "details": [
    {
      "productId": 1,
      "quantity": 1
    },
    {
      "productId": 2,
      "quantity": 2
    }
  ]
}
```

### SaleDetailResponseDto

```json
{
  "id": 1,
  "productId": 1,
  "productName": "Laptop Lenovo",
  "quantity": 1,
  "unitPrice": 3200.00,
  "subtotal": 3200.00
}
```

### SaleResponseDto

```json
{
  "id": 1,
  "customerName": "Juan Perez",
  "description": "Compra de equipos",
  "details": [
    {
      "id": 1,
      "productId": 1,
      "productName": "Laptop Lenovo",
      "quantity": 1,
      "unitPrice": 3200.00,
      "subtotal": 3200.00
    }
  ],
  "total": 3200.00,
  "createdAt": "2026-03-18T20:30:00",
  "updatedAt": "2026-03-18T20:30:00"
}
```

## Endpoints

## Auth

### `POST /auth/login`

- Metodo: `POST`
- Path: `/auth/login`
- Autenticacion: no requerida
- Objetivo: generar token JWT

#### Request body

```json
{
  "username": "admin019",
  "password": "admin123!"
}
```

#### Response `200 OK`

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

#### Posibles errores

- `500 Internal Server Error` si la autenticacion falla mediante excepcion de negocio no manejada especificamente

## Productos

### `GET /api/products`

- Metodo: `GET`
- Path: `/api/products`
- Autenticacion: requerida
- Objetivo: listar todos los productos

#### Response `200 OK`

```json
[
  {
    "id": 1,
    "name": "Laptop Lenovo",
    "description": "Laptop 16GB RAM",
    "unitaryPrice": 3200.00,
    "stock": 10,
    "createdAt": "2026-03-18T20:30:00",
    "updatedAt": "2026-03-18T20:30:00"
  }
]
```

### `GET /api/products/{id}`

- Metodo: `GET`
- Path: `/api/products/{id}`
- Path params:
  - `id`: id del producto
- Autenticacion: requerida

#### Response `200 OK`

Devuelve un `ProductResponseDto`.

#### Posibles errores

- `404 Not Found` si el producto no existe

### `POST /api/products`

- Metodo: `POST`
- Path: `/api/products`
- Autenticacion: requerida
- Objetivo: crear producto

#### Request body

Usa `ProductRequestDto`.

#### Response `201 Created`

Devuelve el producto creado.

#### Validaciones

- `name` obligatorio
- `unitaryPrice` obligatorio y mayor o igual a `0`
- `stock` obligatorio y mayor o igual a `0`

### `PUT /api/products/{id}`

- Metodo: `PUT`
- Path: `/api/products/{id}`
- Autenticacion: requerida
- Objetivo: actualizar producto existente

#### Response `200 OK`

Devuelve el producto actualizado.

### `DELETE /api/products/{id}`

- Metodo: `DELETE`
- Path: `/api/products/{id}`
- Autenticacion: requerida
- Objetivo: eliminar producto

#### Response `204 No Content`

Sin cuerpo de respuesta.

## Ventas

### `GET /api/sales`

- Metodo: `GET`
- Path: `/api/sales`
- Autenticacion: requerida
- Objetivo: listar ventas con detalle y total calculado

#### Response `200 OK`

Lista de `SaleResponseDto`.

### `GET /api/sales/{id}`

- Metodo: `GET`
- Path: `/api/sales/{id}`
- Path params:
  - `id`: id de la venta
- Autenticacion: requerida

#### Response `200 OK`

Devuelve una venta con:

- datos generales
- lista de detalles
- total calculado desde subtotales

### `POST /api/sales`

- Metodo: `POST`
- Path: `/api/sales`
- Autenticacion: requerida
- Objetivo: registrar venta

#### Request body

Usa `SaleRequestDto`.

#### Reglas de negocio

- la venta debe tener al menos un detalle
- cada detalle referencia un producto existente
- el stock debe ser suficiente
- el precio unitario se toma del producto actual
- al guardar la venta se descuenta stock

#### Response `201 Created`

Devuelve la venta creada con sus detalles y total.

#### Errores comunes

- `400 Bad Request` si no hay detalles o si el stock es insuficiente
- `404 Not Found` si un producto no existe

### `PUT /api/sales/{id}`

- Metodo: `PUT`
- Path: `/api/sales/{id}`
- Autenticacion: requerida
- Objetivo: actualizar venta

#### Reglas de negocio

- primero se restaura stock de los detalles actuales
- luego se reemplazan los detalles por los nuevos
- despues se vuelve a descontar stock segun la nueva solicitud

#### Response `200 OK`

Devuelve la venta actualizada.

### `DELETE /api/sales/{id}`

- Metodo: `DELETE`
- Path: `/api/sales/{id}`
- Autenticacion: requerida
- Objetivo: eliminar venta

#### Regla de negocio

- al eliminar la venta se devuelve al stock la cantidad de cada producto involucrado

#### Response `204 No Content`

Sin cuerpo.

## Detalle de ventas

### `GET /api/sales/{saleId}/details`

- Metodo: `GET`
- Path: `/api/sales/{saleId}/details`
- Path params:
  - `saleId`: id de la venta
- Autenticacion: requerida
- Objetivo: listar detalles asociados a una venta

#### Response `200 OK`

Lista de `SaleDetailResponseDto`.

#### Errores comunes

- `404 Not Found` si la venta no existe

### `GET /api/sales/{saleId}/details/{detailId}`

- Metodo: `GET`
- Path: `/api/sales/{saleId}/details/{detailId}`
- Path params:
  - `saleId`: id de la venta
  - `detailId`: id del detalle
- Autenticacion: requerida
- Objetivo: consultar un detalle puntual dentro de la venta

#### Response `200 OK`

Devuelve un `SaleDetailResponseDto`.

#### Errores comunes

- `404 Not Found` si la venta no existe
- `404 Not Found` si el detalle no pertenece a la venta

