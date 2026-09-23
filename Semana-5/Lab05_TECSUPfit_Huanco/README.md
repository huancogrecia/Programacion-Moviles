# TECSUP Fit

## Funcionalidades

- Visualización de clases disponibles
- Filtros de Hoy y Esta semana
- Visualización del detalle de una clase
- Reserva de cupo
- Confirmación de reserva
- Visualización de reservas y sus estados
- Sección de rutinas
- Sección de perfil con datos y estadísticas
- Navegación entre Inicio, Reservas, Rutinas y Perfil

## Evidencias

### Pantalla de inicio
<img width="236" height="496" alt="image" src="https://github.com/user-attachments/assets/67608012-2cd7-4a6c-b1d5-1c2123b5d653" />

### Detalle de clase
<img width="250" height="509" alt="image" src="https://github.com/user-attachments/assets/add84f32-51e6-4714-a2bd-8f03f6dbc58c" />

### Confirmación de reserva
<img width="245" height="513" alt="image" src="https://github.com/user-attachments/assets/6d9b2d3b-07ee-468f-8152-42fea1a1ba56" />

### Mis reservas
<img width="237" height="501" alt="image" src="https://github.com/user-attachments/assets/ab00e7b0-abef-45b0-a993-54b3da667568" />

### Rutinas
<img width="238" height="504" alt="image" src="https://github.com/user-attachments/assets/4d1cb4c0-f96d-48d7-acae-3ca39b7a25b6" />

### Perfil
<img width="229" height="500" alt="image" src="https://github.com/user-attachments/assets/fc577f96-8485-42df-95a6-6e4129c19c30" />

## Preguntas

### 1. ¿Cómo llega la clase elegida en Inicio hasta la pantalla de confirmación?

Cuando elijo una clase en Inicio, sus datos pasan a la pantalla donde se muestra el detalle de la clase. Después continúo con la reserva, selecciono el horario y finalmente esos datos llegan a la pantalla de confirmación, donde se muestra el resumen.

La ruta que sigue es:

Inicio → Detalle de clase → Reserva → Confirmación.

Los datos están definidos en el código y se van pasando entre las pantallas mediante la navegación.


### 2. ¿Cómo sabe el bottomBar cuál ícono resaltar en cada pantalla?

El bottomBar revisa en qué ruta o pantalla me encuentro. Cada opción tiene una ruta y, cuando esa ruta coincide con la pantalla actual, esa opción aparece seleccionada.

Así se puede saber visualmente si estoy en Inicio, Mis Reservas, Rutinas o Perfil.


### 3. ¿Por qué la selección del horario se comporta como un RadioButton, aunque visualmente sean "chips"?

Porque solo puedo elegir un horario a la vez. Cuando selecciono otro horario, este reemplaza al que había elegido antes.

Entonces, aunque visualmente parezcan chips, funcionan como un RadioButton porque solo permiten tener una opción seleccionada.


### 4. ¿Qué tuviste que corregir del código que te generó la IA para tu mejora de la Fase 2?

Tuve que revisar que los cambios hechos con IA no afectaran la navegación ni lo que ya funcionaba en mi aplicación. También verifiqué que no agregara cosas más avanzadas que todavía no hemos aprendido.

Además, hice que cuando reserve una clase esta pueda aparecer en Mis Reservas durante el uso de la aplicación. Como estamos trabajando con datos estáticos y no usamos una base de datos, la información no queda guardada cuando se cierra la aplicación.
