# Aprendizaje

## Cambios entre versiones

- **V1:** devuelve los objetos directamente y fija el código 201 con `@ResponseStatus`.
- **V2:** usa `ResponseEntity`, que permite controlar el código y las cabeceras. Al crear una tarea añade la cabecera `Location` con la URL de la tarea nueva.
- Las dos guardan las tareas en una lista en memoria dentro del controlador (se pierden al apagar la app) y no tienen capas.

## TDD con GET /v1/tasks/{id}

1. Escribí primero los tests (tarea existente y 404) y comprobé que fallaban (rojo): `Status expected:<200> but was:<404>`.
2. Implementé lo mínimo en el controlador: buscar en la lista y lanzar `ResponseStatusException` con 404 si no existe (verde).
3. El test del 404 ya pasaba antes de implementar, porque Spring responde 404 a cualquier ruta que no conoce. Después de implementar, el 404 lo devuelve mi código.

## Decisiones

- Probé los controladores con `@WebMvcTest` y `MockMvc`, que solo cargan la capa web.
- Como el estado vive dentro del controlador, cada test crea los datos que necesita.

## Responsabilidades de las capas

Por ahora solo he hecho controladores con datos en memoria. Todavía no he implementado servicio ni repositorio; eso llegará con el slice JPA.
