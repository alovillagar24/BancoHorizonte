import java.util.ArrayDeque;
import java.util.Deque;

/**
 * GestorHistorial: Implementa una estructura Pila (LIFO - Last In, First Out).
 * Su propósito es proporcionar acceso de complejidad O(1) al registro más reciente,
 * facilitando la revisión inmediata del flujo de operaciones del sistema.
 */
public class GestorHistorial {

    // Deque configurado como Pila (LIFO) para el seguimiento cronológico inverso
    private Deque<SolicitudBancaria> pila = new ArrayDeque<>();

    /**
     * Agrega una nueva solicitud al tope de la pila.
     * Incluye validación de seguridad para evitar inserciones nulas.
     * @param s SolicitudBancaria a registrar.
     */
    public void apilar(SolicitudBancaria s) {
        if (s != null) {
            pila.push(s);
        }
    }

    /**
     * Retorna la incidencia más reciente sin extraerla de la estructura.
     * @return SolicitudBancaria más reciente o null si la pila está vacía.
     */
    public SolicitudBancaria verUltimo() {
        return pila.peek();
    }

    /**
     * Itera sobre la pila para visualizar el historial completo en orden cronológico inverso
     * (de la incidencia más reciente a la más antigua).
     */
    public void mostrarTodo() {
        if (pila.isEmpty()) {
            System.out.println("Historial vacío.");
            return;
        }
        for (SolicitudBancaria s : pila) {
            System.out.println(s);
        }
    }
}
