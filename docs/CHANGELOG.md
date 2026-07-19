Changelog - BANCO HORIZONTE
Todos los cambios notables en este proyecto serán documentados en este archivo. El formato se basa en Keep a Changelog.

[1.2.0] - 2026-07-19
Añadido
Interfaz Visual: Implementación del método imprimirEncabezado() con arte ASCII personalizado para la marca "BANCO HORIZONTE".
Documentación: Creación de la estructura base para la documentación técnica y de referencias (docs/).

Corregido
Estética: Ajuste preciso de espaciado y caracteres en el arte ASCII del encabezado para asegurar una lectura correcta en consola.
Instanciación: Corrección en la llamada al GestorHistorial para asegurar la correcta integración del manejo de pila.

[1.1.0] - 2026-07-15
Añadido
Núcleo de Datos: Implementación completa de los gestores (GestorAtencion, GestorHistorial, GestorArbol, GestorGrafo).
Controller: Integración de la lógica de propagación de datos en MainBanco.java.
Sincronización: Lógica de inserción unificada para mantener coherencia entre todas las estructuras de datos tras cada registro.

Corregido
Lógica: Resolución de conflictos en la implementación de HashMap y Deque.

[1.0.0] - 2026-06-01
Añadido
Estructura inicial del proyecto y modelo básico de SolicitudBancaria.
Diseño del esquema de base de datos conceptual para "Café Andrómeda" (base del modelo actual).

Notas para mantenerlo actualizado:
Añadido: Para nuevas funcionalidades.
Corregido: Para arreglos de bugs o errores de lógica.
Cambios: Para modificaciones importantes en funcionalidades existentes.
