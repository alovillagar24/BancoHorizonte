import java.util.List;

/**
 * GestorArbol: Administra la estructura jerárquica del sistema de incidencias.
 * Facilita la organización de datos bajo el esquema: Raíz -> Tipo -> Subtipo -> Caso.
 */
public class GestorArbol {

    // Nodo raíz que funge como punto de entrada del árbol
    private NodoArbol raiz;

    public GestorArbol(String nombreRaiz) {
        this.raiz = new NodoArbol(nombreRaiz);
    }

    public NodoArbol getRaiz() { return raiz; }

    /**
     * Inserta una solicitud en la jerarquía especificada.
     * Si los nodos de categoría no existen, los crea dinámicamente.
     */
    public void insertarSolicitud(String nombreTipo, String nombreSubtipo, SolicitudBancaria nuevaSolicitud) {
        if (nuevaSolicitud == null) return;

        // Navegar o crear niveles jerárquicos
        NodoArbol nodoTipo = buscarOCrear(this.raiz, nombreTipo);
        NodoArbol nodoSubtipo = buscarOCrear(nodoTipo, nombreSubtipo);

        // Crear el nodo hoja (el caso final) y vincularlo
        NodoArbol nuevoCaso = new NodoArbol(nuevaSolicitud);
        nodoSubtipo.agregarHijo(nuevoCaso);

        System.out.println("Solicitud " + nuevaSolicitud.getIdSolicitud() + " registrada bajo: " + nombreTipo + " -> " + nombreSubtipo);
    }

    /**
     * Método auxiliar para búsqueda jerárquica o instanciación de nuevos nodos.
     * @param padre Nodo actual bajo el cual buscar o insertar.
     * @param nombre Nombre del nodo destino.
     * @return El nodo encontrado o recién creado.
     */
    private NodoArbol buscarOCrear(NodoArbol padre, String nombre) {
        if (padre == null) return null;

        for (NodoArbol hijo : padre.getHijos()) {
            if (hijo.getNombre().equalsIgnoreCase(nombre)) {
                return hijo;
            }
        }
        // Creación y vinculación del nuevo nivel si no existe
        NodoArbol nuevoNodo = new NodoArbol(nombre);
        padre.agregarHijo(nuevoNodo);
        return nuevoNodo;
    }

    /**
     * Realiza un recorrido recursivo en preorden para representar la jerarquía.
     * @param nodo Nodo actual a procesar.
     * @param indentacion Espaciado para visualización de niveles.
     */
    public void mostrarJerarquia(NodoArbol nodo, String indentacion) {
        if (nodo == null) return;

        System.out.println(indentacion + "- " + nodo.getNombre());

        for (NodoArbol hijo : nodo.getHijos()) {
            mostrarJerarquia(hijo, indentacion + "    ");
        }
    }
}
