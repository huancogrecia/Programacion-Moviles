# Prompts utilizados - Laboratorio 06

## Prompt 1

Tengo una aplicación TECSUP Store en Jetpack Compose donde cada producto tiene un DropdownMenu con la opción Favoritos.

Quiero mejorar el proyecto para que al seleccionar Favoritos el producto se guarde en una lista de favoritos.

Mantén el código sencillo y considera solamente lo trabajado hasta la Semana 6. No agregues MVVM, Room ni base de datos.

## Prompt 2

Ya tengo funcionando la selección de productos favoritos desde el DropdownMenu.

Quiero agregar un badge con contador en la opción Favoritos del NavigationDrawer para mostrar cuántos productos fueron marcados como favoritos.

El contador debe actualizarse cuando se agregue un producto a Favoritos. Mantén la estructura actual del proyecto y no agregues nuevas arquitecturas.

## Preguntas de reflexión

### ¿Cómo tuviste que estructurar tu código para que el contador de favoritos del Drawer se entere de lo que pasa en el DropdownMenu de cada producto?

Guardé los productos seleccionados en una lista de favoritos. Cuando selecciono Favoritos desde el DropdownMenu, la lista se actualiza y el Drawer utiliza la cantidad de productos para mostrar el contador.

### ¿Qué tuviste que corregir del código que te generó la IA para la mejora del badge de favoritos?

Tuve que adaptar el código generado a la estructura que ya tenía mi proyecto para conectar la lista de favoritos con el contador mostrado en el NavigationDrawer.