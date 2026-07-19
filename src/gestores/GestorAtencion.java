import java.util.PriorityQueue;
import java.util.Queue;

/**
 * GestorAtencion: Administra la cola de solicitudes pendientes utilizando
 * una PriorityQueue para asegurar el procesamiento basado en el nivel de urgencia.
 */
public class GestorAtencion {

    // Cola de prioridad: ordena automáticamente según el compareTo de SolicitudBancaria
    private Queue<SolicitudBancaria> colaPendientes = new PriorityQueue<>();

    /**
     * Inserta una solicitud en la cola de atención.
     * La posición será determinada automáticamente por la prioridad definida.
     */
    public void encolarSolicitud(SolicitudBancaria solicitud) {
        if (solicitud != null) {
            colaPendientes.offer(solicitud);
        }
    }

    /**
     * Extrae y retorna la solicitud con mayor prioridad (menor valor numérico).
     * @return SolicitudBancaria la siguiente en ser atendida, o null si está vacía.
     */
    public SolicitudBancaria atenderSiguiente() {
        return colaPendientes.poll();
    }

    /**
     * Verifica si existen solicitudes pendientes en la estructura.
     */
    public boolean hayPendientes() {
        return !colaPendientes.isEmpty();
    }

    /**
     * Retorna la solicitud de mayor prioridad sin extraerla de la cola.
     * Útil para vistas previas de atención.
     */
    public SolicitudBancaria verPrimero() {
        return colaPendientes.peek();
    }

    /**
     * Retorna el número actual de solicitudes en espera.
     */
    public int obtenerCantidadPendientes() {
        return colaPendientes.size();
    }
}
