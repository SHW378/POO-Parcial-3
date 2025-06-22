
import java.util.*;

public class Grafo<T> {

    private final Map<T, List<T>> listaAdyacencia;

    public Grafo() {
        this.listaAdyacencia = new HashMap<>();
    }

    public void agregarTarea(T tarea) {
        listaAdyacencia.putIfAbsent(tarea, new ArrayList<>());
    }

    public void eliminarTarea(T tarea) {
        // Eliminar la tarea como destino de otras dependencias
        for (List<T> dependencias : listaAdyacencia.values()) {
            dependencias.remove(tarea);
        }
        listaAdyacencia.remove(tarea);
    }

    public boolean agregarDependencia(T origen, T destino) {
        agregarTarea(origen);
        agregarTarea(destino);

        if (creaCiclo(origen, destino)) {
            return false;
        }

        listaAdyacencia.get(origen).add(destino);
        return true;
    }

    public void eliminarDependencia(T origen, T destino) {
        if (listaAdyacencia.containsKey(origen)) {
            listaAdyacencia.get(origen).remove(destino);
        }
    }

    public List<T> obtenerDependencias(T tarea) {
        return new ArrayList<>(listaAdyacencia.getOrDefault(tarea, new ArrayList<>()));
    }

    public List<T> obtenerPredecesores(T tarea) {
        List<T> predecesores = new ArrayList<>();
        for (Map.Entry<T, List<T>> entry : listaAdyacencia.entrySet()) {
            if (entry.getValue().contains(tarea)) {
                predecesores.add(entry.getKey());
            }
        }
        return predecesores;
    }

    public boolean contieneTarea(T tarea) {
        return listaAdyacencia.containsKey(tarea);
    }

    public List<T> recorridoBFS(T inicio) {
        List<T> resultado = new ArrayList<>();
        Set<T> visitados = new HashSet<>();
        Queue<T> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            T actual = cola.poll();
            resultado.add(actual);

            for (T adyacente : listaAdyacencia.getOrDefault(actual, new ArrayList<>())) {
                if (!visitados.contains(adyacente)) {
                    visitados.add(adyacente);
                    cola.add(adyacente);
                }
            }
        }
        return resultado;
    }

    public List<T> recorridoDFS(T inicio) {
        List<T> resultado = new ArrayList<>();
        Set<T> visitados = new HashSet<>();
        dfs(inicio, visitados, resultado);
        return resultado;
    }

    private void dfs(T tarea, Set<T> visitados, List<T> resultado) {
        if (!visitados.contains(tarea)) {
            visitados.add(tarea);
            resultado.add(tarea);

            for (T adyacente : listaAdyacencia.getOrDefault(tarea, new ArrayList<>())) {
                dfs(adyacente, visitados, resultado);
            }
        }
    }

    private boolean creaCiclo(T origen, T destino) {
        if (origen.equals(destino)) {
            return true;
        }

        Set<T> visitados = new HashSet<>();
        Queue<T> cola = new LinkedList<>();
        cola.add(destino);

        while (!cola.isEmpty()) {
            T actual = cola.poll();
            if (actual.equals(origen)) {
                return true;
            }

            if (!visitados.contains(actual)) {
                visitados.add(actual);
                for (T adyacente : listaAdyacencia.getOrDefault(actual, new ArrayList<>())) {
                    cola.add(adyacente);
                }
            }
        }
        return false;
    }
}
