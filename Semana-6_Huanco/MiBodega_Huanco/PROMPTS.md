# PROMPTS UTILIZADOS CON IA

## Prompt 1 - Búsqueda de productos en tiempo real
Estoy desarrollando una aplicación llamada Mi Bodega en Kotlin con Jetpack Compose, actualmente tengo una pantalla de Inicio que muestra los productos mediante LazyColumn y las categorías Todos, Bebidas, Abarrotes y Snacks mediante LazyRow, el filtro por categoría ya funciona correctamente y no quiero modificar ni eliminar esa funcionalidad, también tengo un campo de búsqueda que visualmente ya aparece en la pantalla pero todavía no debe considerarse funcional, necesito implementar la mejora solicitada para que este campo filtre los productos en tiempo real mientras el usuario escribe, además el buscador debe trabajar junto con la categoría seleccionada, por ejemplo, si selecciono Bebidas y escribo Coca solamente deben mostrarse los productos que pertenezcan a Bebidas y que además coincidan con el texto ingresado, si el campo de búsqueda está vacío debe mantenerse únicamente el filtro de categoría, y si está seleccionada la categoría Todos debe buscar entre todos los productos, realiza únicamente esta mejora en InicioScreen.kt, conserva la estructura actual del proyecto, LazyColumn, LazyRow, NavigationBar y las demás funcionalidades que ya están implementadas, no crees nuevas pantallas, no agregues ViewModel, Room ni base de datos, tampoco modifiques la navegación ni el carrito, utiliza el estado existente textoBusqueda con remember y mutableStateOf, explícame exactamente qué parte del código debo modificar y dame solamente el código necesario para implementar esta mejora sin alterar lo que ya funciona

## Prompt 2 - Mejora del texto ingresado en el buscador
Ahora revisa la búsqueda en tiempo real que acabamos de implementar en InicioScreen.kt, quiero mantener el funcionamiento actual donde el buscador trabaja junto con el filtro de categorías, pero mejora la búsqueda para que también funcione correctamente cuando el usuario escriba espacios al inicio o al final del texto, para ello utiliza trim() antes de comparar el texto ingresado con el nombre del producto, mantén ignoreCase = true para que no importe si se escribe con mayúsculas o minúsculas, no cambies la NavigationBar, LazyRow, LazyColumn, carrito ni ninguna otra pantalla, tampoco agregues nuevas funcionalidades que no sean necesarias, realiza solamente los cambios relacionados con esta mejora del buscador y explícame brevemente qué modificaste y por qué


## Preguntas de reflexión

1. ¿Por qué Producto.kt y MainActivity.kt se entregaron completos, y las pantallas no? ¿Qué tienen en común los archivos que sí se dejaron como esqueleto?
Producto.kt y MainActivity.kt se entregaron completos porque sirven como base para iniciar la aplicación, mientras que las pantallas se dejaron como esqueleto porque en ellas debíamos aplicar los componentes y conceptos aprendidos, como la navegación, LazyRow, LazyColumn, estados y eventos, además los archivos con esqueleto tenían una estructura inicial y los TODO indicaban las partes que debíamos completar.
2. ¿Cómo lograste que el filtro de categoría y el cálculo del carrito reaccionen automáticamente?
Utilicé estados de Jetpack Compose y trabajé con las colecciones de productos y del carrito, cuando cambia la categoría se vuelve a obtener la lista de productos que corresponde, de la misma manera, cuando aumenta o disminuye la cantidad de un producto, Compose vuelve a calcular el subtotal y el total automáticamente, por eso no fue necesario agregar un botón para recalcular.
3. ¿Qué diferencia notaste entre navigate() normal y el que usa popUpTo?
Con navigate() se abre una nueva pantalla manteniendo las anteriores en el historial, por eso podemos regresar a ellas, en cambio con popUpTo podemos eliminar determinadas pantallas del historial, en Mi Bodega lo utilizamos después de confirmar el pedido para evitar regresar al carrito o a los datos de entrega como si el pedido todavía estuviera pendiente.
4. ¿Qué tuviste que corregir del código que te generó la IA para el buscador en tiempo real?
Primero se agregó la condición de búsqueda para que funcionara junto con el filtro de categorías y no lo reemplazara, luego mejoramos el código generado utilizando trim() para eliminar espacios al inicio y al final del texto, también mantuvimos ignoreCase = true para que la búsqueda funcionara sin importar el uso de mayúsculas o minúsculas, finalmente probamos los cambios en el emulador para comprobar su funcionamiento.
5. ¿En qué caso usarías NavigationDrawer y NavigationBar?
Utilizaría NavigationDrawer cuando una aplicación tenga varias opciones o secciones que puedan mostrarse en un menú lateral, mientras que utilizaría NavigationBar cuando existan pocas secciones principales que necesiten estar disponibles constantemente en la parte inferior, como sucede en Mi Bodega con Inicio, Categorías, Pedidos y Perfil.

## Observaciones
1. Durante el desarrollo observé que trabajar a partir de un código esqueleto facilitó la organización del proyecto, ya que cada pantalla tenía su propio archivo y una estructura definida, sin embargo, fue necesario completar y probar cada funcionalidad para asegurar que la navegación y los componentes trabajaran correctamente.
2. También observé que el buscador debía funcionar junto con el filtro de categorías y no reemplazarlo, por ello, durante la fase de mejora con IA realicé pruebas en el emulador y mejoré la búsqueda para que también reconozca correctamente textos escritos con espacios adicionales y diferentes combinaciones de mayúsculas y minúsculas.

## Conclusiones
1. Concluyo que trabajar desde un código esqueleto me permitió concentrarme en aplicar los temas aprendidos durante el curso, como NavigationBar, LazyRow, LazyColumn, navegación entre pantallas, manejo de estados y popUpTo, además pude comprender mejor cómo se relacionan estos componentes dentro de una aplicación completa.
2. Finalmente, considero que la Fase 1 me permitió desarrollar y comprobar las funcionalidades principales por mi cuenta, mientras que en la Fase 2 utilicé la IA como apoyo para mejorar el buscador en tiempo real, revisando los cambios propuestos y probándolos en el emulador antes de incorporarlos al proyecto.











