# Prompts utilizados

## Prompt 1

Texto enviado a Gemini:

Tengo una app Kotlin con Jetpack Compose, Material 3, MVVM, Room y una interface AyudaRepository, agrega en el panel de Soporte el tiempo promedio de resolución de los tickets cerrados usando fechaCierre menos fecha, coloca la consulta en TicketDao, declara la función en AyudaRepository e impleméntala en AyudaRepositoryRoom, luego expón el resultado como StateFlow en PanelViewModel para que PanelScreen solo lo muestre, sin agregar comentarios ni cambiar dependencias.

Qué generó la IA:

Agregó la consulta del promedio y conectó las capas mediante el Repository y el ViewModel, además creó una tarjeta en el panel para mostrar el resultado en horas y minutos.

Qué tuve que corregir yo:

No fueron necesarias correcciones, comprobé que compilara y que el promedio coincidiera con los datos guardados.

Pregunta: ¿Respeta MVVM?

Respuesta: Sí, porque la consulta está en el DAO y el ViewModel usa la interface AyudaRepository, mientras la pantalla solo muestra el resultado.

## Prompt 2

Texto enviado a Gemini:

Agrega a mi app un contador de SLA por prioridad, con un plazo de 4 horas para Alta, 24 horas para Media y 72 horas para Baja desde la creación del ticket, calcula el tiempo restante en la capa ViewModel y muéstralo en las listas y detalles de ambos perfiles, además actualízalo cada minuto y muestra “SLA vencido” cuando termine el plazo, conservando la fecha del ticket y respetando MVVM, sin agregar comentarios ni cambiar dependencias.

Qué generó la IA:

Agregó el cálculo compartido del SLA y lo conectó con las tarjetas y detalles de ambos perfiles, mientras los tickets resueltos o cerrados muestran su estado.

Qué tuve que corregir yo:

Pedí a Gemini conservar la fecha porque la había reemplazado por el SLA, también pedí mejorar el mensaje cuando queda menos de un minuto y después se corrigió la etiqueta repetida “SLA: SLA vencido”.

Pregunta: ¿Respeta MVVM?

Respuesta: Sí, porque el tiempo se calcula en la capa ViewModel y las pantallas solo muestran el texto recibido, sin acceder al DAO ni al Repository.

## Prompt 3

Texto enviado a Gemini:

Agrega a la bandeja de Soporte una búsqueda avanzada que combine estado, prioridad, categoría y texto por título o número de ticket, realiza los filtros en BandejaViewModel usando Flow y combine y agrega los controles en BandejaScreen, conservando el orden por prioridad, la fecha y el SLA, además muestra un mensaje cuando no haya coincidencias y respeta MVVM, sin agregar comentarios ni cambiar dependencias.

Qué generó la IA:

Agregó el buscador y los selectores de prioridad y categoría, combinándolos con los chips de estado existentes para mostrar solo los tickets que cumplen todos los filtros.

Qué tuve que corregir yo:

Gemini corrigió un error al combinar los flujos durante la generación, después comprobé la búsqueda por título y número, los filtros juntos y el mensaje sin resultados, por lo que no fueron necesarias correcciones manuales adicionales.

Pregunta: ¿Respeta MVVM?

Respuesta: Sí, porque el ViewModel obtiene los datos mediante AyudaRepository y los filtra, mientras la pantalla observa el resultado y envía las acciones.

## Reflexión

¿Qué mejoró la IA y qué no?

Me ayudó a agregar el promedio de resolución, el SLA y la búsqueda avanzada, aunque algunos detalles de presentación necesitaron ajustes.

¿Qué error cometió?

Reemplazó la fecha del ticket por el SLA y repitió una etiqueta, además tuvo que corregir la combinación de los flujos.

¿Qué aprendí sobre MVVM, Room y reglas de negocio?

Aprendí que Room realiza las consultas, el Repository comunica los datos y el ViewModel aplica las reglas, mientras la pantalla solo muestra el resultado.
