public class Nodo {
    private SolicitudBancaria solicitud;
    private Nodo siguiente;

    public Nodo(SolicitudBancaria solicitud) {
        this.solicitud = solicitud;
        this.siguiente = null;
    }

    public SolicitudBancaria getSolicitud() { return solicitud; }
    public Nodo getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}
