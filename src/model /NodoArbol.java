import java.util.ArrayList;
import java.util.List;

/**
 * Representa un nodo en la jerarquía del sistema de incidencias.
 * Puede funcionar como categoría, subtipo o como un nodo hoja (caso final).
 */
public class NodoArbol {
    private String nombre; // Identificador del nivel (ej: "Operativa", "Fraude")
    private SolicitudBancaria solicitud; // Solo si el nodo es una hoja (caso final)
    private List<NodoArbol> hijos; // Referencia dinámica a los descendientes

    // Constructor para nodos de clasificación (Nivel raíz o intermedio)
    public NodoArbol(String nombre) {
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
    }

    // Constructor para nodos hoja (casos específicos)
    public NodoArbol(SolicitudBancaria solicitud) {
        this.solicitud = solicitud;
        this.nombre = solicitud.getIdSolicitud();
        this.hijos = new ArrayList<>();
    }

    // Métodos de acceso
    public String getNombre() { return nombre; }
    public SolicitudBancaria getSolicitud() { return solicitud; }
    public List<NodoArbol> getHijos() { return hijos; }

    // Método para agregar un descendiente
    public void agregarHijo(NodoArbol hijo) {
        this.hijos.add(hijo);
    }
}
