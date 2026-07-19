import java.util.ArrayList;
import java.util.List;

/**
 * NodoArbol: Representa un nodo en la estructura jerárquica del sistema.
 * Soporta tanto nodos de clasificación (categorías) como nodos hoja, los cuales
 * contienen la instancia final de la SolicitudBancaria.
 */
public class NodoArbol {

    // Identificador del nivel o ID de la solicitud
    private String nombre;

    // Solicitud asociada, únicamente poblada si el nodo es de tipo hoja
    private SolicitudBancaria solicitud;

    // Lista de nodos descendientes
    private List<NodoArbol> hijos;

    /**
     * Constructor para nodos de clasificación (Nivel raíz o intermedio).
     */
    public NodoArbol(String nombre) {
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
    }

    /**
     * Constructor para nodos hoja (casos específicos de incidencia).
     */
    public NodoArbol(SolicitudBancaria solicitud) {
        this.solicitud = solicitud;
        this.nombre = solicitud.getIdSolicitud();
        this.hijos = new ArrayList<>();
    }

    // --- Métodos de acceso ---

    public String getNombre() { return nombre; }

    public SolicitudBancaria getSolicitud() { return solicitud; }

    public List<NodoArbol> getHijos() { return hijos; }

    /**
     * Determina si el nodo actual es una hoja (no tiene descendientes).
     */
    public boolean esHoja() {
        return hijos.isEmpty();
    }

    /**
     * Agrega un nuevo nodo descendiente a la estructura.
     * @param hijo NodoArbol a insertar en la jerarquía.
     */
    public void agregarHijo(NodoArbol hijo) {
        if (hijo != null) {
            this.hijos.add(hijo);
        }
    }
}
