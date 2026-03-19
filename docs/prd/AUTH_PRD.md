# PRD - Autenticacion y seguridad

## Nombre de la funcionalidad

Autenticacion JWT y proteccion de endpoints.

## Problema a resolver

El sistema necesita controlar acceso a los endpoints de negocio para evitar consultas o modificaciones sin autenticacion.

## Objetivo

Permitir que un usuario se autentique, obtenga un token JWT y use ese token para consumir endpoints protegidos.

## Alcance funcional

- login con usuario y password
- emision de token JWT
- proteccion de endpoints REST
- integracion con Swagger mediante boton `Authorize`

## Usuario objetivo

- desarrollador backend
- consumidor de la API
- tester funcional

## Requerimientos funcionales

1. El sistema debe exponer `POST /auth/login` sin autenticacion.
2. El sistema debe devolver un JWT cuando las credenciales sean validas.
3. El sistema debe requerir JWT para productos, ventas y detalle de ventas.
4. El sistema debe permitir probar endpoints autenticados desde Swagger.

## Requerimientos no funcionales

- autenticacion stateless
- compatibilidad con cliente frontend y Swagger
- manejo simple de errores

## Criterios de aceptacion

- sin token no se accede a `/api/products`
- con token valido si se accede
- Swagger muestra `Authorize`
- `/auth/login` funciona sin token

## Riesgos conocidos

- credenciales hardcodeadas
- clave JWT dentro del codigo
- no hay gestion de usuarios persistidos

## Evolucion recomendada

- mover usuarios a base de datos
- mover secreto JWT a variables de entorno
- agregar expiracion configurable y refresh tokens
- incorporar roles y autorizacion por perfil

