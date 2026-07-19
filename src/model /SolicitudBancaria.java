import java.time.LocalDateTime;

public class SolicitudBancaria implements Comparable<SolicitudBancaria> {

    // ==========================================
    // 1. ATRIBUTOS (Encapsulamiento estricto)
    // ==========================================
    // Atributos inmutables (no pueden cambiar una vez creados)
    private final String idSolicitud;
    private final LocalDateTime fechaRegistro;
    private final int numeroTurno;

    // Atributos mutables (pueden cambiar durante el ciclo de vida del caso)
    private String tipoSolicitud;
    private String descripcion;
    private int nivelPrioridad;
    private String estado;

    // ==========================================
    // 2. CONSTRUCTOR ESPERADO
    // ==========================================
    public SolicitudBancaria(String idSolicitud, String tipoSolicitud, String descripcion,
                             int nivelPrioridad, int numeroTurno) {
        // Datos ingresados en la captura
        this.idSolicitud = idSolicitud;
        this.tipoSolicitud = tipoSolicitud;
        this.descripcion = descripcion;
        this.nivelPrioridad = nivelPrioridad;
        this.numeroTurno = numeroTurno;

        // Datos asignados automáticamente por el sistema (Reglas operativas)
        this.estado = "PENDIENTE";
        this.fechaRegistro = LocalDateTime.now();
    }

    // ==========================================
    // 3. MÉTODOS BÁSICOS (Getters)
    // ==========================================
    public String getIdSolicitud() { return idSolicitud; }
    public String getTipoSolicitud() { return tipoSolicitud; }
    public String getDescripcion() { return descripcion; }
    public int getNivelPrioridad() { return nivelPrioridad; }
    public String getEstado() { return estado; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public int getNumeroTurno() { return numeroTurno; }

    // ==========================================
    // 4. MÉTODOS DE MODIFICACIÓN (Setters Controlados)
    // ==========================================
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setNivelPrioridad(int nuevaPrioridad) {
        this.nivelPrioridad = nuevaPrioridad;
    }

    // Método operativo para la gestión y resolución de la incidencia
    public void agregarNotaSeguimiento(String nota) {
        this.descripcion = this.descripcion + " | [Actualización]: " + nota;
    }

    // ==========================================
    // 5. REPRESENTACIÓN DEL OBJETO
    // ==========================================
    @Override
    public String toString() {
        return String.format("[%s] Turno: %d | Tipo: %s | Prioridad: %d | Estado: %s",
                idSolicitud, numeroTurno, tipoSolicitud, nivelPrioridad, estado);
    }

    // Implementación requerida para la Cola de Prioridad
    @Override
    public int compareTo(SolicitudBancaria otra) {
        // Orden descendente: el valor más alto (prioridad 3) se atiende primero
        return Integer.compare(otra.getNivelPrioridad(), this.getNivelPrioridad());
    }
}
