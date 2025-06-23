
public class Tarea {

    private int id;
    private String nombre;
    private String descripcion;
    private int duracionHoras;
    private int prioridad;
    private EstadoTarea estado;

    public enum EstadoTarea {
        PENDIENTE,
        EN_PROGRESO,
        COMPLETADA,
        BLOQUEADA
    }

    public Tarea(int id, String nombre, String descripcion, int duracionHoras, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionHoras = duracionHoras;
        this.prioridad = prioridad;
        this.estado = EstadoTarea.PENDIENTE; // Estado inicial
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    // Cambiar estado con métodos explícitos
    public void setEstadoPENDIENTE() {
        this.estado = EstadoTarea.PENDIENTE;
    }

    public void setEstadoEN_PROGRESO() {
        this.estado = EstadoTarea.EN_PROGRESO;
    }

    public void setEstadoCOMPLETADA() {
        this.estado = EstadoTarea.COMPLETADA;
    }

    public void setEstadoBLOQUEADA() {
        this.estado = EstadoTarea.BLOQUEADA;
    }

    // Imprime esta tarea usando un arreglo de tareas (para compatibilidad)
    public void imprimir(Tarea tarea[]) {
        System.out.println("Tarea " + tarea[id].getId() + "[ Nombre: " + tarea[id].getNombre()
                + ", Descripcion: " + tarea[id].getDescripcion() + ", Duracion en horas: " + tarea[id].getDuracionHoras()
                + ", Prioridad: " + tarea[id].getPrioridad() + ", Estado: " + tarea[id].getEstado() + "]");
    }

    public boolean cambiarEstado(EstadoTarea nuevoEstado, boolean dependenciasResueltas) {
        switch (estado) {
            case PENDIENTE -> {
                if ((nuevoEstado == EstadoTarea.EN_PROGRESO || nuevoEstado == EstadoTarea.COMPLETADA) && dependenciasResueltas) {
                    estado = nuevoEstado;
                    return true;
                }
                if (nuevoEstado == EstadoTarea.BLOQUEADA) {
                    estado = EstadoTarea.BLOQUEADA;
                    return true;
                }
            }
            case EN_PROGRESO -> {
                if (nuevoEstado == EstadoTarea.COMPLETADA) {
                    estado = EstadoTarea.COMPLETADA;
                    return true;
                }
                if (nuevoEstado == EstadoTarea.BLOQUEADA) {
                    estado = EstadoTarea.BLOQUEADA;
                    return true;
                }
            }
            case BLOQUEADA -> {
                if (nuevoEstado == EstadoTarea.PENDIENTE && dependenciasResueltas) {
                    estado = EstadoTarea.PENDIENTE;
                    return true;
                }
            }
            default -> {
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tarea{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + ", duracionHoras=" + duracionHoras
                + ", prioridad=" + prioridad
                + ", estado=" + estado
                + '}';
    }
}
