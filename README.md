BANCO HORIZONTE
Sistema Profesional de Gestión de Incidencias Bancarias

Descripción
BANCO HORIZONTE es una plataforma de software diseñada para la gestión, trazabilidad y resolución eficiente de incidencias dentro del sector financiero. El sistema implementa una arquitectura robusta que centraliza la lógica de negocio a través de un controlador principal, asegurando la integridad de la información mediante el uso de estructuras de datos avanzadas como colas de prioridad, pilas, árboles y grafos.

Características Principales
---> Gestión de Atención: Priorización de solicitudes mediante PriorityQueue para una respuesta rápida a casos críticos.
---> Trazabilidad Histórica: Registro persistente y ordenado de cada incidencia utilizando estructuras Deque.
---> Modelado Relacional: Implementación de grafos y árboles para visualizar la jerarquía y relaciones entre incidencias.
---> Interfaz Profesional: Interfaz de consola con diseño ASCII de alta precisión para una experiencia de usuario distinguida.
---> Arquitectura Modular: Código estructurado para facilitar el mantenimiento y la escalabilidad de nuevas funcionalidades.

Estructura del Proyecto
├── docs/                # Documentación técnica y académica
├── src/
│   ├── gestores/        # Lógica de procesamiento y estructuras de datos
│   └── model/           # Definición de entidades (Nodos, Solicitudes)
├── .gitignore           # Archivos ignorados por el control de versiones
├── CHANGELOG.md         # Historial cronológico de cambios
├── LICENSE              # Licencia de uso
└── README.md            # Este documento

Requisitos
---> Java SE Development Kit (JDK) 21 o superior.
---> Entorno de ejecución compatible con terminales UTF-8 para la correcta renderización del arte ASCII.

Licencia
---> Este proyecto se distribuye bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

Referencias
---> Para obtener información detallada sobre los fundamentos algorítmicos utilizados, consulta el archivo docs/REFERENCIAS.md.
