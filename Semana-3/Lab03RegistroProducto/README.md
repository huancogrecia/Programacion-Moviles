# Laboratorio 03 - Registro de Producto

## Estudiante
Grecia Huanco

## Descripción
Aplicación desarrollada en Kotlin con Jetpack Compose que permite registrar productos, calculando el importe total (precio × cantidad).

## Capturas

### Pantalla inicial (Figura 1)
<img width="253" height="563" alt="image" src="https://github.com/user-attachments/assets/5fb5281b-d750-4400-9774-03c6a678b458" />


### Producto registrado (Figura 2)

 <img width="252" height="554" alt="image" src="https://github.com/user-attachments/assets/8bfa6d88-45a1-44c2-ad2b-4bc30121637b" />

## Respuesta: ¿Qué pasaría si declaras las variables de los campos SIN remember?

Si declaras las variables sin `remember`, el valor se reinicia en cada recomposición. Al escribir en un TextField, la pantalla se redibuja y la variable vuelve a su valor inicial, por lo que el campo no mostraría lo que el usuario escribe. La variable se pierde y el texto no se actualiza.
