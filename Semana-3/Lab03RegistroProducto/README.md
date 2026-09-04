# Laboratorio 03 - Registro de Producto

## Estudiante
Grecia Huanco

## Descripción
Aplicación desarrollada en Kotlin con Jetpack Compose que permite registrar productos, calculando el importe total (precio × cantidad).

## Capturas

### Pantalla inicial (Fi
gura 1)
<img width="253" height="563" alt="image" src="https://github.com/user-attachments/assets/5fb5281b-d750-4400-9774-03c6a678b458" />


### Producto registrado (Figura 2)

 <img width="252" height="554" alt="image" src="https://github.com/user-attachments/assets/8bfa6d88-45a1-44c2-ad2b-4bc30121637b" />

## Respuesta: ¿Qué pasaría si declaras las variables de los campos SIN remember?

Si declaras las variables sin `remember`, el valor se reinicia en cada recomposición. Al escribir en un TextField, la pantalla se redibuja y la variable vuelve a su valor inicial, por lo que el campo no mostraría lo que el usuario escribe. La variable se pierde y el texto no se actualiza

## Mejora con IA

| Prompt que usé                                                                                                                                                                                                                                                             | Qué generó Gemini | Qué acepté o corregí (y por qué)                                                                                                                                                                   |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Tengo una app en Jetpack Compose con PantallaRegistro que tiene tres campos: nombre, precio y cantidad, un botón AGREGAR PRODUCTO que muestra una Card con el resumen y un mensaje verde de confirmación. Necesito agregar validación de campos vacíos y un botón LIMPIAR. | Gemini generó validación de campos vacíos (mensaje de error en rojo) y un botón LIMPIAR que vacía el formulario. | Corregí la posición del botón LIMPIAR (siempre visible) y agregué `enabled` para que solo se active si hay datos, porque no tiene sentido que el botón esté habilitado si no hay nada que limpiar. |

### Pantalla inicial con IA (Figura 1)
![Captura de pantalla 2026-09-03 a la(s) 8.46.31 p. m..png](../../../../../../var/folders/3s/r21l4rpx4ygdhcsf7lr49tgm0000gn/T/TemporaryItems/NSIRD_screencaptureui_ejl5sD/Captura%20de%20pantalla%202026-09-03%20a%20la%28s%29%208.46.31%E2%80%AFp.%E2%80%AFm..png)

### Pantalla con los datos con IA se activa el botón de LIMPIAR (Figura 2)
![Captura de pantalla 2026-09-03 a la(s) 8.50.57 p. m..png](../../../../../../var/folders/3s/r21l4rpx4ygdhcsf7lr49tgm0000gn/T/TemporaryItems/NSIRD_screencaptureui_ypqHz3/Captura%20de%20pantalla%202026-09-03%20a%20la%28s%29%208.50.57%E2%80%AFp.%E2%80%AFm..png)

### Pantalla con los productos registrados (Figura 3)
![Captura de pantalla 2026-09-03 a la(s) 8.52.31 p. m..png](../../../../../../var/folders/3s/r21l4rpx4ygdhcsf7lr49tgm0000gn/T/TemporaryItems/NSIRD_screencaptureui_KOkJI3/Captura%20de%20pantalla%202026-09-03%20a%20la%28s%29%208.52.31%E2%80%AFp.%E2%80%AFm..png)