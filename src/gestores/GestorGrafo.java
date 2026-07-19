import java.util.*;

public class GestorGrafo {
    // Mapa: Sucursal -> Lista de IDs de solicitudes asociadas
    private Map<String, List<String>> adyacencia = new HashMap<>();

    // Registrar una relación entre una sucursal y una solicitud
    public void agregarRelacion(String nombreSucursal, String idSolicitud) {
        adyacencia.putIfAbsent(nombreSucursal, new ArrayList<>());
        adyacencia.get(nombreSucursal).add(idSolicitud);
    }

    // Mostrar el grafo (Relaciones de solicitudes por sucursal)
    public void mostrarGrafo() {
        System.out.println("\n--- MAPA DE RELACIONES: SUCURSALES Y SOLICITUDES ---");
        for (String sucursal : adyacencia.keySet()) {
            System.out.println("Sucursal: " + sucursal + " gestiona -> " + adyacencia.get(sucursal));
        }
    }
}
