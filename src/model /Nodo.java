/**
 * Clase Nodo: Componente fundamental de la estructura de datos Lista Enlazada.
 * Actúa como contenedor de la información de la solicitud, permitiendo la
 * interconexión secuencial para la auditoría y trazabilidad cronológica.
 */
public class Nodo {
    // Referencia al objeto de la solicitud bancaria contenida en este nodo
    private SolicitudBancaria solicitud;

    // Puntero al siguiente nodo en la secuencia de la lista (siguiente elemento cronológico)
    private Nodo siguiente;

    /**
     * Constructor del nodo. Inicializa el nodo con una solicitud específica
     * y establece el puntero siguiente como nulo por defecto.
     */
    public Nodo(SolicitudBancaria solicitud) {
        this.solicitud = solicitud;
        this.siguiente = null;
    }

    // Retorna la instancia de SolicitudBancaria almacenada
    public SolicitudBancaria getSolicitud() { return solicitud; }

    // Obtiene la referencia al siguiente nodo en la cadena
    public Nodo getSiguiente() { return siguiente; }

    // Establece el enlace al siguiente nodo de la lista
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}
