# PRD - Ventas y detalle de ventas

## Nombre de la funcionalidad

Registro y consulta de ventas con detalle de productos.

## Problema a resolver

El sistema necesita registrar ventas reales, asociar multiples productos por venta y reflejar el impacto en inventario.

## Objetivo

Permitir crear ventas con detalle, consultar su composicion y mantener consistencia de stock.

## Alcance funcional

- crear venta con multiples items
- listar ventas
- consultar venta por id
- actualizar venta
- eliminar venta
- consultar detalles por venta
- consultar un detalle individual dentro de una venta

## Usuario objetivo

- operador comercial
- administrador del sistema

## Requerimientos funcionales

1. El sistema debe permitir registrar una venta para un cliente.
2. Cada venta debe incluir uno o mas detalles.
3. Cada detalle debe referenciar un producto existente.
4. El sistema debe descontar stock al crear o actualizar ventas.
5. El sistema debe restaurar stock al eliminar una venta.
6. El sistema debe permitir consultar el detalle de cada venta.

## Reglas de negocio

- una venta debe tener al menos un detalle
- cada detalle debe tener cantidad minima de `1`
- no puede venderse mas stock del disponible
- el precio de detalle se captura desde el producto al momento de la venta
- el total se calcula por suma de subtotales

## Criterios de aceptacion

- crear venta valida responde `201`
- crear venta sin detalles responde `400`
- crear venta con stock insuficiente responde `400`
- consultar venta responde datos generales, detalles y total
- consultar detalles por venta responde lista de items vendidos
- eliminar venta restaura stock

## Dependencias

- autenticacion JWT
- tabla `sale`
- tabla `sale_detail`
- tabla `product`

## Riesgos conocidos

- no existe estado de venta
- no hay manejo de pagos
- no hay cancelacion parcial
- no existe auditoria de cambios por usuario

## Evolucion recomendada

- agregar estado de venta
- agregar medios de pago
- guardar impuestos y descuentos
- incorporar historial o auditoria

