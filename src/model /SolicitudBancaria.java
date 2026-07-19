/**
 * Clase modelo que representa una incidencia dentro del sistema Banco Horizonte.
 * Implementa Comparable para facilitar la gestión en colas de prioridad.
 */
public class SolicitudBancaria implements Comparable<SolicitudBancaria> {

    // Identificador único generado mediante marca de tiempo para trazabilidad (INC-1-timestamp)
    private String idSolicitud;

    // Categoría operativa (ej. 'Técnica', 'Fraude', 'Atención al Cliente')
    private String tipo;

    // Descripción detallada de la incidencia
    private String descripcion;

    // Nivel de urgencia: 1 (Alta), 2 (Media), 3 (Baja)
    private int prioridad;

    // Turno asignado para el ordenamiento cronológico
    private int turno;

    // Estado del ciclo de vida: REGISTRADA, ATENDIDO
    private String estado;

    /**
     * Constructor principal. Inicializa la solicitud con los datos necesarios
     * para su trazabilidad en todas las estructuras de datos del sistema.
     */
    public SolicitudBancaria(String idSolicitud, String tipo, String descripcion, int prioridad, int turno) {
        this.idSolicitud = idSolicitud;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.turno = turno;
        this.estado = "REGISTRADA";
    }

    /**
     * Define el criterio de ordenamiento para la PriorityQueue.
     * Basado en el valor de prioridad: menor valor numérico indica mayor urgencia.
     */
    @Override
    public int compareTo(SolicitudBancaria otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    // --- Métodos de acceso (Getters/Setters) ---

    public String getIdSolicitud() { return idSolicitud; }

    public String getEstado() { return estado; }

    /**
     * Permite la actualización del estado conforme la solicitud avanza
     * por los distintos gestores del sistema.
     */
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Prioridad: %d | Estado: %s | Turno: %d",
                idSolicitud, tipo, prioridad, estado, turno);
    }
}
