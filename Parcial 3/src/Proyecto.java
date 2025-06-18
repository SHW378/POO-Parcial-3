
import java.util.*;

public class Proyecto {

    /*  como quedaria quicksort y heapsort


    // Método auxiliar para intercambiar elementos en una lista
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

     */
}
