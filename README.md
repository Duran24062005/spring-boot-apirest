# First Spring Boot App

API REST construida con Spring Boot para la gestion de productos, ventas y detalle de ventas, con autenticacion JWT y documentacion OpenAPI/Swagger.

## Objetivo del proyecto

Este proyecto modela un flujo basico de inventario y ventas:

- administrar productos
- registrar ventas con multiples detalles
- descontar stock automaticamente al vender
- consultar detalle de venta por venta o por item
- proteger endpoints con JWT
- exponer documentacion en Swagger UI

## Stack tecnico

- Java 17
- Spring Boot 3.5.11
- Spring Web
- Spring Data JPA
- MySQL
- Spring Security
- JJWT 0.11.5
- Springdoc OpenAPI
- Lombok
- Thymeleaf

## Modulos funcionales

### 1. Autenticacion

- endpoint publico para login
- generacion de token JWT
- proteccion de endpoints mediante filtro JWT

### 2. Productos

- crear producto
- listar productos
- consultar producto por id
- actualizar producto
- eliminar producto

### 3. Ventas

- crear venta con multiples items
- listar ventas
- consultar venta por id
- actualizar venta
- eliminar venta
- recalculo de stock al crear, actualizar o eliminar

### 4. Detalle de venta

- consultar todos los detalles asociados a una venta
- consultar un detalle puntual dentro de una venta

## Estructura de documentacion

- [Referencia API](docs/API_REFERENCE.md)
- [Arquitectura y modelo de datos](docs/ARCHITECTURE.md)
- [PRD de autenticacion](docs/prd/AUTH_PRD.md)
- [PRD de productos](docs/prd/PRODUCTS_PRD.md)
- [PRD de ventas](docs/prd/SALES_PRD.md)

## Estructura del proyecto

```text
src/main/java/com/spring_boot/first_spring_boot_app
├── config
├── controllers
├── dtos
├── exceptions
├── model
├── repositories
├── security
└── service
```

## Ejecucion local

### Requisitos

- Java 17
- Maven Wrapper
- MySQL activo
- base de datos `sales_db`

### Configuracion de base de datos

La conexion actual esta en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sales_db
spring.datasource.username=alexidg
spring.datasource.password=12345
```

Puedes crear e insertar datos base con:

```sql
source database.sql;
```

## Levantar el proyecto

```bash
./mvnw spring-boot:run
```

## Documentacion Swagger

Con la app corriendo:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Autenticacion

1. Ejecuta `POST /auth/login`
2. Obtiene el token JWT
3. Usa el boton `Authorize` en Swagger
4. Consume los endpoints protegidos

## Notas importantes

- todos los endpoints de negocio estan protegidos salvo `/auth/login` y recursos de Swagger
- la venta exige al menos un detalle
- al crear o actualizar una venta se valida stock
- al eliminar una venta se restaura el stock de sus productos

