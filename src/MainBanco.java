import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class MainBanco {
    // La lista enlazada actúa como el Historial (Pila-Bitácora): registra todo el flujo cronológicamente
    private static Nodo cabeza = null;
    private static int contadorTurnos = 0;
    private static int totalRegistros = 0; // Contador para el formato de ID
    private static Scanner scanner = new Scanner(System.in);

    // El gestor utiliza una Cola (Queue - FIFO) para el flujo operativo de atención
    private static GestorAtencion gestor = new GestorAtencion();

    // Nueva estructura Pila (LIFO) para acceso instantáneo al historial reciente
    private static Deque<SolicitudBancaria> pilaHistorial = new ArrayDeque<>();

    // Mapa para consulta eficiente (O(1)) mediante ID de solicitud
    private static Map<String, SolicitudBancaria> mapaSolicitudes = new HashMap<>();

    // Gestor para la estructura jerárquica de incidencias
    private static GestorArbol gestorJerarquico = new GestorArbol("Banco Horizonte");

    // Grafo representado mediante clase externa
    private static GestorGrafo gestorGrafo = new GestorGrafo();

    // Método para validar las reglas de negocio
    private static boolean esValido(String tipo, int prio) {
        List<String> tiposValidos = Arrays.asList("APERTURA", "FRAUDE", "ACLARACION", "BLOQUEO");
        if (!tiposValidos.contains(tipo.toUpperCase())) {
            System.out.println("Error: Tipo no válido. Opciones: " + tiposValidos);
            return false;
        }
        if (prio < 1 || prio > 3) {
            System.out.println("Error: La prioridad debe ser un valor entre 1 y 3.");
            return false;
        }
        return true;
    }

    /**
     * Inserta una nueva solicitud al final de la Lista Maestra (Lista Enlazada).
     * Esta estructura preserva el orden cronológico absoluto para auditoría.
     */
    private static void insertarEnListaMaestra(SolicitudBancaria solicitud) {
        Nodo nuevoNodo = new Nodo(solicitud);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            // Navega hasta el último nodo disponible
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    // Método recursivo para búsqueda por ID en la Lista Enlazada
    public static SolicitudBancaria buscarPorIdRecursivo(Nodo nodoActual, String idBuscado) {
        // Caso base: lista vacía o fin de la búsqueda
        if (nodoActual == null) {
            return null;
        }
        // Caso base: se encontró el elemento
        if (nodoActual.getSolicitud().getIdSolicitud().equals(idBuscado)) {
            return nodoActual.getSolicitud();
        }
        // Llamada recursiva: avanzar al siguiente nodo
        return buscarPorIdRecursivo(nodoActual.getSiguiente(), idBuscado);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- BANCO HORIZONTE: GESTIÓN DE INCIDENCIAS ---");
            System.out.println("1. Registrar nueva solicitud");
            System.out.println("2. Atender siguiente solicitud (Cola)");
            System.out.println("3. Consultar próximo elemento en cola");
            System.out.println("4. Revisar último registro en historial");
            System.out.println("5. Ver todo el historial completo");
            System.out.println("6. Consultar por ID (Folio)");
            System.out.println("7. Salir");
            System.out.println("8. Buscar registro por ID (Recursivo)");
            System.out.println("9. Visualizar jerarquía de incidencias");
            System.out.println("10. Visualizar mapa de relaciones (Grafo)");
            System.out.print("Seleccione una opción: ");

            String inputOpcion = scanner.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(inputOpcion);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarSolicitud();
                case 2 -> procesarAtencion();
                case 3 -> consultarProximoCola();
                case 4 -> revisarUltimoHistorial();
                case 5 -> verTodoElHistorial();
                case 6 -> consultarPorFolio();
                case 7 -> salir = true;
                case 8 -> {
                    System.out.print("Ingrese el ID a buscar recursivamente: ");
                    String id = scanner.nextLine();
                    SolicitudBancaria resultado = buscarPorIdRecursivo(cabeza, id);
                    if (resultado != null) {
                        System.out.println("Encontrado: " + resultado);
                    } else {
                        System.out.println("El ID no existe en la lista enlazada.");
                    }
                }
                case 9 -> {
                    System.out.println("\n--- ESTRUCTURA JERÁRQUICA ---");
                    gestorJerarquico.mostrarJerarquia(gestorJerarquico.getRaiz(), "");
                }
                case 10 -> gestorGrafo.mostrarGrafo();
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // Operación: REGISTRO
    private static void registrarSolicitud() {
        System.out.print("Tipo (APERTURA, FRAUDE, ACLARACION, BLOQUEO): ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Prioridad (1 baja, 2 media, 3 alta): ");
        int prio = Integer.parseInt(scanner.nextLine());
        System.out.print("Sucursal de origen: ");
        String sucursal = scanner.nextLine();

        if (desc.trim().isEmpty() || !esValido(tipo, prio)) return;

        totalRegistros++;
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String idGenerado = String.format("INC-%s-%s-%04d", tipo.substring(0, 3), fecha, totalRegistros);

        SolicitudBancaria nueva = new SolicitudBancaria(idGenerado, tipo, desc, prio, ++contadorTurnos);

        insertarEnListaMaestra(nueva);
        pilaHistorial.push(nueva);
        gestor.encolarSolicitud(nueva);
        mapaSolicitudes.put(nueva.getIdSolicitud(), nueva);

        // Registro en el árbol jerárquico
        String categoria = tipo.equalsIgnoreCase("APERTURA") ? "Operativa" : "Gestión";
        gestorJerarquico.insertarSolicitud(categoria, tipo, nueva);

        // Registro en el grafo
        gestorGrafo.agregarRelacion(sucursal, idGenerado);

        System.out.println("Registro exitoso: " + idGenerado);
    }

    private static void consultarPorFolio() {
        System.out.print("Ingrese el ID de la solicitud: ");
        String id = scanner.nextLine();
        SolicitudBancaria encontrada = mapaSolicitudes.get(id);

        if (encontrada != null) {
            System.out.println("Resultado: " + encontrada);
        } else {
            System.out.println("Error: ID no encontrado.");
        }
    }

    private static void procesarAtencion() {
        if (gestor.hayPendientes()) {
            SolicitudBancaria atendida = gestor.atenderSiguiente();
            atendida.setEstado("ATENDIDO");
            System.out.println(">>> Procesando: " + atendida.toString());
        } else {
            System.out.println("No hay solicitudes pendientes.");
        }
    }

    private static void consultarProximoCola() {
        if (gestor.hayPendientes()) {
            System.out.println("Próximo: " + gestor.verPrimero());
        } else {
            System.out.println("No hay solicitudes pendientes.");
        }
    }

    private static void revisarUltimoHistorial() {
        if (pilaHistorial.isEmpty()) {
            System.out.println("Historial vacío.");
        } else {
            System.out.println("Último: " + pilaHistorial.peek());
        }
    }

    private static void verTodoElHistorial() {
        System.out.println("\n--- HISTORIAL COMPLETO ---");
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getSolicitud());
            actual = actual.getSiguiente();
        }
    }
}
