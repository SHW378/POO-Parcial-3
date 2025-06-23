import java.util.*;

public class Proyecto {
    private String nombre;
    private String descripcion;
    private Map<Integer, Tarea> tareas;
    private Grafo<Integer> grafoDependencias;

    public Proyecto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tareas = new HashMap<>();
        this.grafoDependencias = new Grafo<>();
    }

    // Crear tarea con ID controlado externamente
    public Tarea crearTarea(int id, String nombre, String descripcion, int duracionHoras, int prioridad) {
        Tarea nueva = new Tarea(id, nombre, descripcion, duracionHoras, prioridad);
        tareas.put(nueva.getId(), nueva);
        grafoDependencias.agregarTarea(nueva.getId());
        return nueva;
    }

    public boolean eliminarTarea(int id) {
        if (tareas.containsKey(id)) {
            tareas.remove(id);
            grafoDependencias.eliminarTarea(id);
            return true;
        }
        return false;
    }

    public String imprimirTarea(int id) {
        Tarea t = tareas.get(id);
        return (t != null) ? t.toString() : "El registro está vacío.";
    }

    public boolean agregarDependencia(int idPredecesor, int idSucesor) {
        return grafoDependencias.agregarDependencia(idPredecesor, idSucesor);
    }

    public void eliminarDependencia(int idPredecesor, int idSucesor) {
        grafoDependencias.eliminarDependencia(idPredecesor, idSucesor);
    }

    public List<Integer> dependenciasDe(int id) {
        return grafoDependencias.obtenerDependencias(id);
    }

    public boolean actualizarEstadoTarea(int id, Tarea.EstadoTarea nuevoEstado) {
        Tarea tarea = tareas.get(id);
        if (tarea == null) return false;
        if (tarea.getEstado() == Tarea.EstadoTarea.BLOQUEADA)
            return false;
        if (nuevoEstado == Tarea.EstadoTarea.EN_PROGRESO || nuevoEstado == Tarea.EstadoTarea.COMPLETADA) {
            for (int pre : grafoDependencias.obtenerPredecesores(id)) {
                Tarea tpre = tareas.get(pre);
                if (tpre != null && tpre.getEstado() != Tarea.EstadoTarea.COMPLETADA)
                    return false;
            }
        }
        return tarea.cambiarEstado(nuevoEstado, true);
    }

    public List<Tarea> filtrarPorEstado(Tarea.EstadoTarea estado) {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea t : tareas.values()) {
            if (t.getEstado() == estado) resultado.add(t);
        }
        return resultado;
    }

    public List<Tarea> filtrarPorPrioridad(int prioridad) {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea t : tareas.values()) {
            if (t.getPrioridad() == prioridad) resultado.add(t);
        }
        return resultado;
    }

    public List<Tarea> tareasDisponibles() {
        Set<Integer> disponibles = new HashSet<>();
        for (Integer id : tareas.keySet()) {
            boolean disponible = true;
            for (int predecesor : grafoDependencias.obtenerPredecesores(id)) {
                Tarea pre = tareas.get(predecesor);
                if (pre == null || pre.getEstado() != Tarea.EstadoTarea.COMPLETADA) {
                    disponible = false;
                    break;
                }
            }
            if (disponible && tareas.get(id).getEstado() == Tarea.EstadoTarea.PENDIENTE)
                disponibles.add(id);
        }
        List<Tarea> resultado = new ArrayList<>();
        for (int id : disponibles) resultado.add(tareas.get(id));
        return resultado;
    }

    // ----- Métodos de Ordenamiento -----
    private void intercambiar(List<Tarea> tareas, int i, int j) {
        Tarea temp = tareas.get(i);
        tareas.set(i, tareas.get(j));
        tareas.set(j, temp);
    }

    // QuickSort por prioridad (mayor a menor)
    public List<Tarea> ordenarPorPrioridadQuickSort() {
        List<Tarea> lista = new ArrayList<>(tareas.values());
        quickSortPorPrioridad(lista, 0, lista.size() - 1);
        return lista;
    }

    private void quickSortPorPrioridad(List<Tarea> lista, int low, int high) {
        if (low < high) {
            int pi = partitionPorPrioridad(lista, low, high);
            quickSortPorPrioridad(lista, low, pi - 1);
            quickSortPorPrioridad(lista, pi + 1, high);
        }
    }

    private int partitionPorPrioridad(List<Tarea> lista, int low, int high) {
        int pivot = lista.get(high).getPrioridad();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (lista.get(j).getPrioridad() >= pivot) {
                i++;
                intercambiar(lista, i, j);
            }
        }
        intercambiar(lista, i + 1, high);
        return i + 1;
    }

    // HeapSort por duración (mayor a menor)
    public List<Tarea> ordenarPorDuracionHeapSort() {
        List<Tarea> lista = new ArrayList<>(tareas.values());
        heapSortPorDuracion(lista);
        return lista;
    }

    private void heapSortPorDuracion(List<Tarea> lista) {
        int n = lista.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(lista, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            intercambiar(lista, 0, i);
            heapify(lista, i, 0);
        }
    }

    private void heapify(List<Tarea> lista, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && lista.get(left).getDuracionHoras() > lista.get(largest).getDuracionHoras())
            largest = left;
        if (right < n && lista.get(right).getDuracionHoras() > lista.get(largest).getDuracionHoras())
            largest = right;
        if (largest != i) {
            intercambiar(lista, i, largest);
            heapify(lista, n, largest);
        }
    }
    // ----- Fin métodos de ordenamiento -----

    public String estadisticas() {
        int pendientes = filtrarPorEstado(Tarea.EstadoTarea.PENDIENTE).size();
        int enProgreso = filtrarPorEstado(Tarea.EstadoTarea.EN_PROGRESO).size();
        int completadas = filtrarPorEstado(Tarea.EstadoTarea.COMPLETADA).size();
        int bloqueadas = filtrarPorEstado(Tarea.EstadoTarea.BLOQUEADA).size();
        int totalTareas = pendientes + enProgreso + completadas + bloqueadas;
        int capacidad = 0;
        for (Tarea t : tareas.values()) capacidad += t.getDuracionHoras();
        String completado = (pendientes == 0 && enProgreso == 0 && bloqueadas == 0 && completadas > 0) ? "Si" : "No";

        return "Nombre del proyecto: " + nombre + "\n" +
               "Descripción: " + descripcion + "\n" +
               "Estadísticas:\n" +
               "- Total de tareas: " + totalTareas + "\n" +
               "- Pendientes: " + pendientes + "\n" +
               "- En progreso: " + enProgreso + "\n" +
               "- Completadas: " + completadas + "\n" +
               "- Bloqueadas: " + bloqueadas + "\n" +
               "- Capacidad: " + capacidad + "\n" +
               "- Proyecto completado: " + completado;
    }

    public Tarea getTarea(int id) { return tareas.get(id); }
    public Grafo<Integer> getGrafo() { return grafoDependencias; }
    public Collection<Tarea> getTareas() { return tareas.values(); }
}