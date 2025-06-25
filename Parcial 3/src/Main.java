
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Proyecto proyecto;
    private static int contadorId = 1;

    public static void main(String[] args) {
        System.out.println("=== GESTOR DE PROYECTOS ===");
        String nombreProyecto;
        do {
            System.out.println("Ingrese el nombre del proyecto: ");
            nombreProyecto = scanner.nextLine();
            if (nombreProyecto.isEmpty()) {
                System.out.println("El nombre del proyecto no puede estar vacío.");
            }
        } while (nombreProyecto.isEmpty());

        String descripcionProyecto;
        do {
            System.out.println("Ingrese la descripción del proyecto: ");
            descripcionProyecto = scanner.nextLine();
            if (descripcionProyecto.isEmpty()) {
                System.out.println("La descripción del proyecto no puede estar vacía.");
            }
        } while (descripcionProyecto.isEmpty());

        System.out.print("Ingrese la capacidad máxima de tareas: ");
        int capacidadMaxima = leerEntero();

        proyecto = new Proyecto(nombreProyecto, descripcionProyecto, capacidadMaxima);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero();
            switch (opcion) {
                case 1 ->
                    crearTarea();
                case 2 ->
                    eliminarTarea();
                case 3 ->
                    establecerDependencia();
                case 4 ->
                    eliminarDependencia();
                case 5 ->
                    actualizarEstadoTarea();
                case 6 ->
                    mostrarTareasPorPrioridad();
                case 7 ->
                    mostrarTareasPorDuracion();
                case 8 ->
                    recorridoBFS();
                case 9 ->
                    recorridoDFS();
                case 10 ->
                    mostrarEstadisticas();
                case 11 ->
                    mostrarTareasDisponibles();
                case 12 ->
                    filtrarTareasPorEstado();
                case 13 ->
                    filtrarTareasPorPrioridad();
                case 0 ->
                    System.out.println("Saliendo del programa...");
                default ->
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n-------------- Menú --------------");
        System.out.println("1. Crear tarea");
        System.out.println("2. Eliminar tarea");
        System.out.println("3. Establecer dependencia");
        System.out.println("4. Eliminar dependencia");
        System.out.println("5. Actualizar estado de tarea");
        System.out.println("6. Mostrar tareas ordenadas por prioridad (QuickSort)");
        System.out.println("7. Mostrar tareas ordenadas por duración (HeapSort)");
        System.out.println("8. Recorrido de tareas (BFS)");
        System.out.println("9. Recorrido de tareas (DFS)");
        System.out.println("10. Mostrar estadísticas");
        System.out.println("11. Mostrar tareas disponibles para iniciar");
        System.out.println("12. Filtrar tareas por estado");
        System.out.println("13. Filtrar tareas por prioridad");
        System.out.println("0. Salir");
        System.out.println("-----------------------------------");
        System.out.print("Selecciona una opción: ");
    }

    private static void crearTarea() {
        if (proyecto.getTareas().size() >= proyecto.getCapacidadMaxima()) {
            System.out.println("Ya se alcanzó la capacidad máxima de tareas. No puedes crear más.");
            return;
        }
        System.out.print("Nombre de la tarea: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Duración (horas): ");
        int horas = leerEntero();

        int prioridad;
        do {
            System.out.print("Prioridad (1-5, 5 = alta): ");
            prioridad = leerEntero();
            if (prioridad < 1 || prioridad > 5) {
                System.out.println("La prioridad debe ser un número entre 1 y 5.");
            }
        } while (prioridad < 1 || prioridad > 5);

        Tarea creada = proyecto.crearTarea(contadorId, nombre, desc, horas, prioridad);
        if (creada != null) {
            System.out.printf("Tarea creada: [%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                    creada.getId(), creada.getNombre(), creada.getPrioridad(), creada.getDuracionHoras(), creada.getEstado());
            contadorId++;
        } else {
            System.out.println("No se pudo crear la tarea. (Capacidad máxima alcanzada)");
        }
    }

    private static void eliminarTarea() {
        System.out.print("ID de la tarea a eliminar: ");
        int id = leerEntero();
        if (proyecto.eliminarTarea(id)) {
            System.out.println("Tarea eliminada.");
        } else {
            System.out.println("No existe una tarea con ese ID.");
        }
    }

    private static void establecerDependencia() {
        System.out.print("ID de la tarea prerequisito: ");
        int idPre = leerEntero();
        System.out.print("ID de tarea dependiente: ");
        int idSuc = leerEntero();
        if (proyecto.agregarDependencia(idPre, idSuc)) {
            System.out.println("Dependencia agregada.");
        } else {
            System.out.println("No se pudo agregar (puede que genere un ciclo).");
        }
    }

    private static void eliminarDependencia() {
        System.out.print("ID de tarea predecesora: ");
        int idPre = leerEntero();
        System.out.print("ID de tarea sucesora: ");
        int idSuc = leerEntero();
        if (proyecto.eliminarDependencia(idPre, idSuc)) {
            System.out.println("Dependencia eliminada.");
        } else {
            System.out.println("No existe esa dependencia entre esas tareas.");
        }
    }

    private static void actualizarEstadoTarea() {
        System.out.print("ID de la tarea: ");
        int id = leerEntero();
        System.out.println();
        Tarea tarea = proyecto.getTarea(id);

        if (tarea == null) {
            System.out.println("No existe ninguna tarea con ese ID.");
            return;
        }

        List<Integer> dependencias = proyecto.getGrafo().obtenerPredecesores(id);
        boolean dependenciasIncompletas = false;
        if (!dependencias.isEmpty()) {
            System.out.println("La tarea tiene las siguientes dependencias:");
            for (int depId : dependencias) {
                Tarea tdep = proyecto.getTarea(depId);
                if (tdep != null) {
                    System.out.printf("- ID %d : %s (Estado: %s)%n", tdep.getId(), tdep.getNombre(), tdep.getEstado());
                    System.out.println();
                    if (tdep.getEstado() != Tarea.EstadoTarea.COMPLETADA) {
                        dependenciasIncompletas = true;
                    }
                }
            }
        }

        if (dependenciasIncompletas) {
            System.out.println("ADVERTENCIA: No se puede cambiar el estado porque hay dependencias sin completar.");
            System.out.println("Debe completar todas las dependencias antes de continuar con esta tarea.");
            return;
        }

        if (tarea.getEstado() == Tarea.EstadoTarea.BLOQUEADA) {
            System.out.println("Esta tarea está bloqueada por otra tarea, complétala antes.");
            return;
        }

        System.out.println("Estado actual: " + tarea.getEstado());
        System.out.print("Estados disponibles: ");
        for (Tarea.EstadoTarea estado : Tarea.EstadoTarea.values()) {
            System.out.print(estado + ", ");
        }
        System.out.println();
        System.out.print("Ingrese el nuevo estado: ");
        String nuevoEstadoStr = scanner.nextLine().trim().toUpperCase();

        Tarea.EstadoTarea nuevoEstado;
        try {
            nuevoEstado = Tarea.EstadoTarea.valueOf(nuevoEstadoStr);
        } catch (IllegalArgumentException e) {
            System.out.print("Estado inválido. Estados válidos: ");
            for (Tarea.EstadoTarea estado : Tarea.EstadoTarea.values()) {
                System.out.print(estado + " ");
            }
            System.out.println();
            return;
        }

        if (nuevoEstado == tarea.getEstado()) {
            System.out.println("La tarea ya está en ese estado.");
            return;
        }

        boolean ok = proyecto.actualizarEstadoTarea(id, nuevoEstado);
        System.out.println(ok ? "Estado actualizado." : "No se pudo actualizar (verifica dependencias o tarea bloqueada).");
    }

    private static void mostrarTareasPorPrioridad() {
        var lista = proyecto.ordenarPorPrioridadQuickSort();
        System.out.println("Tareas ordenadas por prioridad (mayor a menor):");
        for (Tarea t : lista) {
            System.out.printf("[%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                    t.getId(), t.getNombre(), t.getPrioridad(), t.getDuracionHoras(), t.getEstado());
        }
    }

    private static void mostrarTareasPorDuracion() {
        var lista = proyecto.ordenarPorDuracionHeapSort();
        System.out.println("Tareas ordenadas por duración (menor a mayor):");
        for (Tarea t : lista) {
            System.out.printf("[%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                    t.getId(), t.getNombre(), t.getPrioridad(), t.getDuracionHoras(), t.getEstado());
        }
    }

    private static void recorridoBFS() {
        System.out.print("ID de tarea inicial para BFS: ");
        int id = leerEntero();
        List<Integer> recorrido = proyecto.getGrafo().recorridoBFS(id);
        System.out.println("Recorrido BFS: ");
        for (int tid : recorrido) {
            Tarea t = proyecto.getTarea(tid);
            if (t != null) {
                System.out.printf("[%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                        t.getId(), t.getNombre(), t.getPrioridad(), t.getDuracionHoras(), t.getEstado());
            }
        }
    }

    private static void recorridoDFS() {
        System.out.print("ID de tarea inicial para DFS: ");
        int id = leerEntero();
        List<Integer> recorrido = proyecto.getGrafo().recorridoDFS(id);
        System.out.println("Recorrido DFS: ");
        for (int tid : recorrido) {
            Tarea t = proyecto.getTarea(tid);
            if (t != null) {
                System.out.printf("[%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                        t.getId(), t.getNombre(), t.getPrioridad(), t.getDuracionHoras(), t.getEstado());
            }
        }
    }

    private static void mostrarEstadisticas() {
        System.out.println(proyecto.estadisticas());
    }

    private static void mostrarTareasDisponibles() {
        var disponibles = proyecto.tareasDisponibles();
        System.out.println("Tareas disponibles para iniciar:");
        for (Tarea t : disponibles) {
            System.out.printf("[%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                    t.getId(), t.getNombre(), t.getPrioridad(), t.getDuracionHoras(), t.getEstado());
        }
    }

    private static void filtrarTareasPorEstado() {
        System.out.print("Estados disponibles: ");
        for (Tarea.EstadoTarea estado : Tarea.EstadoTarea.values()) {
            System.out.print(estado + ", ");
        }
        System.out.println();
        System.out.print("Ingrese el estado para filtrar: ");
        String estadoStr = scanner.nextLine().trim().toUpperCase();

        Tarea.EstadoTarea estado;
        try {
            estado = Tarea.EstadoTarea.valueOf(estadoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Estado inválido.");
            return;
        }

        var filtradas = proyecto.filtrarPorEstado(estado);
        for (Tarea t : filtradas) {
            System.out.printf("[%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                    t.getId(), t.getNombre(), t.getPrioridad(), t.getDuracionHoras(), t.getEstado());
        }
    }

    private static void filtrarTareasPorPrioridad() {
        System.out.print("Prioridad a filtrar (1-5): ");
        int prioridad = leerEntero();
        var filtradas = proyecto.filtrarPorPrioridad(prioridad);
        for (Tarea t : filtradas) {
            System.out.printf("[%d] %s (Prioridad: %d, Duración: %d horas, Estado: %s)%n",
                    t.getId(), t.getNombre(), t.getPrioridad(), t.getDuracionHoras(), t.getEstado());
        }
    }

    private static int leerEntero() {
        while (!scanner.hasNextInt()) {
            scanner.next(); // limpia entrada inválida
            System.out.print("Por favor, ingresa un número: ");
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
}
