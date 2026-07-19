import java.util.*;

/**
 * GestorGrafo: Administra las relaciones de distribución entre sucursales y solicitudes.
 * Utiliza un mapa de adyacencia para mapear cada sucursal con su lista de incidencias
 * asignadas, permitiendo una consulta eficiente.
 */
public class GestorGrafo {

    // Mapa: Sucursal -> Lista de IDs de solicitudes asociadas
    private Map<String, List<String>> adyacencia = new HashMap<>();

    /**
     * Registra una relación vinculando una sucursal con una solicitud específica.
     * @param nombreSucursal Nombre de la sucursal origen.
     * @param idSolicitud Identificador de la solicitud a vincular.
     */
    public void agregarRelacion(String nombreSucursal, String idSolicitud) {
        if (nombreSucursal == null || idSolicitud == null) return;

        adyacencia.putIfAbsent(nombreSucursal, new ArrayList<>());
        adyacencia.get(nombreSucursal).add(idSolicitud);
    }

    /**
     * Visualiza la distribución de incidencias por sucursal.
     * Itera sobre el mapa para presentar las relaciones de gestión actuales.
     */
    public void mostrarGrafo() {
        System.out.println("\n--- MAPA DE RELACIONES: SUCURSALES Y SOLICITUDES ---");

        if (adyacencia.isEmpty()) {
            System.out.println("No existen relaciones registradas.");
            return;
        }

        for (Map.Entry<String, List<String>> entrada : adyacencia.entrySet()) {
            System.out.println("Sucursal: " + entrada.getKey() + " | Gestiona: " + entrada.getValue());
        }
    }

    /**
     * Obtiene el listado de solicitudes para una sucursal específica.
     * @param nombreSucursal Nombre de la sucursal a consultar.
     * @return Lista de IDs de solicitudes.
     */
    public List<String> obtenerSolicitudesPorSucursal(String nombreSucursal) {
        return adyacencia.getOrDefault(nombreSucursal, new ArrayList<>());
    }
}
