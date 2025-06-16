

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

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public boolean CambiarEstado(EstadoTarea nuevoEstado, boolean dependenciasResueltas) {
        
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
