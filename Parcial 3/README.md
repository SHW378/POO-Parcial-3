## Distribución de Responsabilidades

*César – Clase Tarea y Coordinación*
•⁠  ⁠Implementar la clase ⁠ Tarea ⁠ con:
  - Identificador único, nombre, descripción, duración (horas), prioridad (número en rango).
  - Estados: PENDIENTE, EN_PROGRESO, COMPLETADA, BLOQUEADA.
  - Validar transiciones de estado según dependencias.
  - Probar la clase y documentar su uso.
•⁠  ⁠Apoyar la integración y coordinación general.
•⁠  ⁠Entregable: Tarea.java

*José – Clase Grafo*
•⁠  ⁠Implementar la clase ⁠ Grafo ⁠ dirigido para dependencias entre tareas.
•⁠  ⁠Métodos para agregar y eliminar dependencias (aristas).
•⁠  ⁠Implementar detección de ciclos (previo a agregar dependencia).
•⁠  ⁠Implementar recorridos BFS y DFS.
•⁠  ⁠Apoyar integración con Proyecto y Tarea.
•⁠  ⁠Entregable: Grafo.java

*Arturo – Clase Proyecto*
•⁠  ⁠Implementar la clase ⁠ Proyecto ⁠ para coordinar tareas y dependencias.
•⁠  ⁠Métodos para crear/eliminar tarea, establecer/eliminar dependencias y actualizar etapas.
•⁠  ⁠Métodos para listar tareas disponibles (sin predecesores pendientes), filtrar por estado/prioridad.
•⁠  ⁠Implementar estadísticas (total, por estado, completitud, capacidad, etc.).
•⁠  ⁠Apoyar la integración final del sistema.
•⁠  ⁠Entregable: Proyecto.java

*Saulo – Algoritmos de Ordenamiento y Filtros*
•⁠  ⁠Implementar QuickSort (por prioridad, de mayor a menor).
•⁠  ⁠Implementar HeapSort (por duración).
•⁠  ⁠Métodos para mostrar tareas ordenadas, filtrar tareas por estado/prioridad.
•⁠  ⁠Probar ambos algoritmos sobre datos de ejemplo.
•⁠  ⁠Entregable: AlgoritmosOrdenamiento.java

*Ismael – Interfaz de Usuario, Pruebas y UML*
•⁠  ⁠Implementar ⁠ Main.java ⁠ con menú interactivo para:
  - Crear/eliminar tareas.
  - Gestionar dependencias.
  - Actualizar etapas.
  - Mostrar tareas ordenadas y filtradas.
  - Mostrar estadísticas.
  - Recorrer secuencias (BFS, DFS).
  - Salir del programa.
•⁠  ⁠Realizar pruebas integrales del sistema.
•⁠  ⁠Elaborar y revisar el diagrama UML definitivo en PlantUML.
•⁠  ⁠Entregables: Main.java y Diagrama UML

---

## Cronograma y Entregables

### Día 1: Fundamentos y Clases Base
•⁠  ⁠*César:* Clase Tarea implementada y probada (atributos, estados, validaciones).
•⁠  ⁠*José:* Clase Grafo funcional (estructura básica, métodos de dependencia, detección de ciclos).
•⁠  ⁠*Arturo:* Clase Proyecto con estructura y métodos base.
•⁠  ⁠*Saulo:* QuickSort implementado y probado; definición de interfaz de ordenamiento.
•⁠  ⁠*Ismael:* Menú básico diseñado; estructura general de la interfaz.

### Día 2: Integración y Funcionalidades Intermedias
•⁠  ⁠*César y José:* Integrar Tarea y Grafo (dependencias funcionales).
•⁠  ⁠*Arturo:* Implementar estadísticas y métodos de filtrado en Proyecto.
•⁠  ⁠*Saulo:* Implementar HeapSort y pruebas de ambos algoritmos.
•⁠  ⁠*Ismael:* Conectar menú con las operaciones principales; pruebas de funcionalidad básica.

### Día 3: Integración Completa y Pruebas
•⁠  ⁠*Todos:* Integrar todas las clases y funcionalidades; pruebas funcionales y de integración.
•⁠  ⁠*Ismael:* Ajustes y validaciones en la interfaz; pruebas de menú.
•⁠  ⁠*Arturo:* Verificación de estadísticas, filtrado y estados.
•⁠  ⁠*José:* Pruebas de recorridos BFS/DFS y manejo de dependencias.

### Día 4: Entrega Final y Documentación
•⁠  ⁠*Todos:* Pruebas finales, depuración de errores, verificación de criterios de entrega.
•⁠  ⁠*JOSE:* Elaboración y revisión del diagrama UML definitivo en PlantUML.
•⁠  ⁠*César:* Verificación de estructura de carpetas, preparación del archivo .zip nombrado por apellido del equipo.

---

## Criterios de Entrega

•⁠  ⁠*Entrega:*  
  - Diagrama UML en código PlantUML en ⁠ /src ⁠.
  - Proyecto comprimido en ⁠ .zip ⁠, nombrado con el apellido de un integrante.
•⁠  ⁠*Revisión:*  
  - Entrega puntual, de lo contrario se descuentan 3 puntos.
  - Por cada error funcional, de compilación, estilo crítico, o discrepancia entre UML y código, se descuentan 0.5 puntos.
•⁠  ⁠*Validaciones:*  
  - Compilación sin errores.
  - Todas las funcionalidades del menú operativas.
  - Detección de ciclos, BFS y DFS funcionando.
  - Algoritmos de ordenamiento correctos.
  - Estadísticas y filtrados implementados.
  - Diagrama UML coincide con código.

---

## Notas Importantes

•⁠  ⁠*Comunicación:* Reuniones y coordinación diaria (presencial o digital).
•⁠  ⁠*Respaldo:* Uso de Git y backup diario individual.
•⁠  ⁠*Enfoque:* Priorizar funcionalidad mínima requerida antes de agregar extras.
•⁠  ⁠*Entrega:* Fecha límite 24 de junio antes de las 18:00 hrs.

---