# Laboratorio 02 - Carrito de Compras con IA

## Estudiante
Grecia Huanco

## Descripción
Este proyecto implementa un carrito de compras en Kotlin utilizando Programación Orientada a Objetos.

El programa permite agregar productos físicos y digitales, mostrar los productos del carrito y calcular el subtotal, IGV, descuento y total final.

## Programación Orientada a Objetos

### Abstracción
Se utiliza la clase abstracta `Producto`, que contiene las características comunes de los productos como nombre y precio.

### Herencia
Las clases `ProductoFisico` y `ProductoDigital` heredan de la clase `Producto`.

### Encapsulamiento
La clase `Carrito` contiene una lista privada de productos mediante `private`, evitando que sea modificada directamente desde fuera de la clase.

### Polimorfismo
Los productos físicos y digitales implementan de manera diferente la función `mostrarTipo()` mediante `override`.

## Funciones principales
- Agregar productos al carrito.
- Mostrar productos físicos y digitales.
- Calcular subtotal.
- Calcular IGV del 18%.
- Calcular descuento utilizando `when`.
- Calcular el total final.

## Resultado de ejecución

![Resultado del Laboratorio 02 con IA](lab2_Huanco_IA.png)