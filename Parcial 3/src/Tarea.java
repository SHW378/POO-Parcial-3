public class Tarea {
    private static int contadorId = 1; 

    private final int id;
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

    public Tarea(String nombre, String descripcion, int duracionHoras, int prioridad) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionHoras = duracionHoras;
        this.prioridad = prioridad;
        this.estado = EstadoTarea.PENDIENTE; // Estado inicial
    }

    public int getId() {
        return id;
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

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    /* checar
    public boolean cambiarEstado(EstadoTarea nuevoEstado, boolean dependenciasResueltas) {
        switch (estado) {
            case PENDIENTE -> {
                if (nuevoEstado == EstadoTarea.EN_PROGRESO && dependenciasResueltas) {
                    estado = EstadoTarea.EN_PROGRESO;
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
*/
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
