import java.util.List;

/**
 * Clase que administra el árbol de incidencias del Banco Horizonte.
 * Se encarga de gestionar la jerarquía (Tipo -> Subtipo -> Caso)
 * y permite el recorrido de los datos.
 */
public class GestorArbol {
    private NodoArbol raiz;

    public GestorArbol(String nombreRaiz) {
        this.raiz = new NodoArbol(nombreRaiz);
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    /**
     * Inserta una solicitud siguiendo la jerarquía: Tipo -> Subtipo -> Caso.
     * Si el tipo o subtipo no existen, los crea automáticamente.
     */
    public void insertarSolicitud(String nombreTipo, String nombreSubtipo, SolicitudBancaria nuevaSolicitud) {
        // Navegar o crear el nodo de Nivel 1 (Tipo)
        NodoArbol nodoTipo = buscarOCrear(this.raiz, nombreTipo);
        // Navegar o crear el nodo de Nivel 2 (Subtipo) dentro del Tipo
        NodoArbol nodoSubtipo = buscarOCrear(nodoTipo, nombreSubtipo);

        // Crear el nodo hoja con la solicitud y agregarlo al Subtipo
        NodoArbol nuevoCaso = new NodoArbol(nuevaSolicitud);
        nodoSubtipo.agregarHijo(nuevoCaso);

        System.out.println("Solicitud " + nuevaSolicitud.getIdSolicitud() + " insertada exitosamente en " + nombreSubtipo);
    }

    /**
     * Método auxiliar para buscar un nodo por nombre o crearlo si no existe.
     */
    private NodoArbol buscarOCrear(NodoArbol padre, String nombre) {
        for (NodoArbol hijo : padre.getHijos()) {
            if (hijo.getNombre().equalsIgnoreCase(nombre)) {
                return hijo;
            }
        }
        // Si no se encuentra, se crea, se vincula y se retorna
        NodoArbol nuevoNodo = new NodoArbol(nombre);
        padre.agregarHijo(nuevoNodo);
        return nuevoNodo;
    }

    /**
     * Recorrido en preorden para mostrar la jerarquía visualmente.
     * Utiliza indentación para representar los niveles del árbol.
     */
    public void mostrarJerarquia(NodoArbol nodo, String indentacion) {
        System.out.println(indentacion + "- " + nodo.getNombre());
        for (NodoArbol hijo : nodo.getHijos()) {
            mostrarJerarquia(hijo, indentacion + "    ");
        }
    }
}
