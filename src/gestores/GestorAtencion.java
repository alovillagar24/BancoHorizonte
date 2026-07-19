import java.util.PriorityQueue;
import java.util.Queue;

public class GestorAtencion {
    // Usamos PriorityQueue como implementación de la interfaz Queue
    private Queue<SolicitudBancaria> colaPendientes = new PriorityQueue<>();

    // Método para agregar a la cola
    public void encolarSolicitud(SolicitudBancaria solicitud) {
        colaPendientes.offer(solicitud);
    }

    // Método para procesar (atender) la siguiente solicitud
    public SolicitudBancaria atenderSiguiente() {
        return colaPendientes.poll(); // Extrae y retorna el primero
    }

    public boolean hayPendientes() {
        return !colaPendientes.isEmpty();
    }

    // Método para consultar el próximo elemento sin extraerlo
    public SolicitudBancaria verPrimero() {
        return colaPendientes.peek();
    }
}
