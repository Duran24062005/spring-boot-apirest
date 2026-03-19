# PRD - Gestion de productos

## Nombre de la funcionalidad

Gestion de catalogo e inventario de productos.

## Problema a resolver

La aplicacion necesita un catalogo de productos que pueda ser usado luego en el flujo de ventas.

## Objetivo

Permitir crear, consultar, actualizar y eliminar productos, manteniendo precio y stock disponibles para ventas.

## Alcance funcional

- alta de productos
- consulta de listado
- consulta por id
- edicion de datos
- eliminacion

## Usuario objetivo

- administrador del sistema
- usuario de inventario

## Requerimientos funcionales

1. El sistema debe permitir crear un producto con nombre, descripcion, precio y stock.
2. El sistema debe listar todos los productos.
3. El sistema debe permitir consultar un producto por id.
4. El sistema debe permitir actualizar sus datos.
5. El sistema debe permitir eliminarlo.

## Reglas de negocio

- `name` es obligatorio
- `unitaryPrice` no puede ser negativo
- `stock` no puede ser negativo

## Criterios de aceptacion

- crear un producto responde `201`
- consultar listado responde `200`
- consultar uno inexistente responde `404`
- validar request invalido responde `400`

## Dependencias

- autenticacion JWT
- tabla `product`

## Riesgos conocidos

- no hay paginacion
- no hay filtros por nombre o stock
- no existe control para impedir eliminar productos ya usados en ventas

