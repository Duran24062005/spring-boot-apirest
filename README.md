# First Spring Boot App

API REST para gestionar productos y ventas con Spring Boot + JPA + MySQL.

## Swagger / OpenAPI

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints

### Productos

- `GET /api/products` - lista todos los productos.
- `GET /api/products/{id}` - obtiene un producto por id.
- `POST /api/products` - crea producto.
- `PUT /api/products/{id}` - actualiza producto.
- `DELETE /api/products/{id}` - elimina producto.

Ejemplo `POST/PUT`:

```json
{
  "name": "Laptop Lenovo",
  "description": "Laptop 16GB RAM",
  "unitaryPrice": 3200.00,
  "stock": 10
}
```

### Ventas

- `GET /api/sales` - lista todas las ventas.
- `GET /api/sales/{id}` - obtiene una venta por id.
- `POST /api/sales` - crea venta y descuenta stock.
- `PUT /api/sales/{id}` - actualiza venta (recalcula detalle y stock).
- `DELETE /api/sales/{id}` - elimina venta y restaura stock.

Ejemplo `POST/PUT`:

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

## Validaciones y errores

- Se validan campos obligatorios, valores numéricos y detalles de venta.
- Si un recurso no existe, responde `404`.
- Si hay error de negocio (ejemplo: stock insuficiente), responde `400`.
- Las respuestas de error se devuelven en formato JSON desde un manejador global.
