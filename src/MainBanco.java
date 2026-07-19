import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador Central: Banco Horizonte.
 * Gestiona la integración de estructuras de datos (Cola, Pila, Lista, Árbol, Grafo, Map)
 * asegurando la coherencia en la prioridad (1:Alta, 2:Media, 3:Baj).
 */
public class MainBanco {

    private static Nodo cabeza = null;
    private static int contadorTurnos = 0;
    private static int totalRegistros = 0;
    private static Scanner scanner = new Scanner(System.in);

    // Instanciación de Gestores
    private static GestorAtencion gestor = new GestorAtencion();
    private static GestorHistorial gestorHistorial = new GestorHistorial(); // Corregido: Uso de la clase gestor
    private static Map<String, SolicitudBancaria> mapaSolicitudes = new HashMap<>();
    private static GestorArbol gestorJerarquico = new GestorArbol("Banco Horizonte");
    private static GestorGrafo gestorGrafo = new GestorGrafo();

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            imprimirEncabezado();
            System.out.println(" 1. Registrar nueva solicitud");
            System.out.println(" 2. Atender siguiente solicitud (Cola)");
            System.out.println(" 3. Consultar próximo elemento en cola");
            System.out.println(" 4. Revisar último registro (Historial)");
            System.out.println(" 5. Ver todo el historial (Lista)");
            System.out.println(" 6. Consultar por ID (HashMap)");
            System.out.println(" 7. Buscar registro (Recursivo)");
            System.out.println(" 8. Visualizar jerarquía (Árbol)");
            System.out.println(" 9. Visualizar relaciones (Grafo)");
            System.out.println(" 0. Salir");
            System.out.println("------------------------------------------------------------");
            System.out.print(" OPTION> ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> registrarSolicitud();
                case "2" -> procesarAtencion();
                case "3" -> consultarProximoCola();
                case "4" -> revisarUltimoHistorial();
                case "5" -> verTodoElHistorial();
                case "6" -> consultarPorFolio();
                case "7" -> buscarRecursivamente();
                case "8" -> gestorJerarquico.mostrarJerarquia(gestorJerarquico.getRaiz(), "");
                case "9" -> gestorGrafo.mostrarGrafo();
                case "0" -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void imprimirEncabezado() {
        System.out.println("\n\n" + "=".repeat(80));
        System.out.println(" ____    _    _   _  ____ ___     _   _  ___  ____  ___ _____ ___  _   _ _____ _____ ");
        System.out.println("| __ )  / \\  | \\ | |/ ___/ _ \\   | | | |/ _ \\|  _ \\|_ _|__  // _ \\| \\ | |_   _| ____|");
        System.out.println("|  _ \\ / _ \\ |  \\| | |  | | | |  | |_| | | | | |_) || |  / /| | | |  \\| | | | |  _|  ");
        System.out.println("| |_) / ___ \\| |\\  | |__| |_| |  |  _  | |_| |  _ < | | / /_| |_| | |\\  | | | | |___ ");
        System.out.println("|____/_/   \\_\\_| \\_|\\____\\___/   |_| |_|\\___/|_| \\_\\___/____|\\___/|_| \\_| |_| |_____|");
        System.out.println();
        System.out.println("                    BANCO HORIZONTE");
        System.out.println("                  SISTEMA DE GESTIÓN v1.2");
        System.out.println("=".repeat(80));
    }

    /**
     * Registro unificado: Centraliza la inserción en todas las estructuras,
     * garantizando que la prioridad [1, 2, 3] sea respetada por la cola.
     */
    private static void registrarSolicitud() {
        System.out.print("Tipo (APERTURA, FRAUDE, ACLARACION, BLOQUEO): ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();

        System.out.print("Prioridad (1 alta, 2 media, 3 baja): ");
        int prio = Integer.parseInt(scanner.nextLine());

        System.out.print("Sucursal de origen: ");
        String sucursal = scanner.nextLine();

        if (desc.trim().isEmpty() || (prio < 1 || prio > 3)) {
            System.out.println("Error: Ingrese una prioridad válida (1-3).");
            return;
        }

        totalRegistros++;
        String idGenerado = String.format("INC-%s-%s-%04d", tipo.substring(0, 3),
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), totalRegistros);

        SolicitudBancaria nueva = new SolicitudBancaria(idGenerado, tipo, desc, prio, ++contadorTurnos);

        // Propagación sincronizada
        insertarEnListaMaestra(nueva);
        gestorHistorial.apilar(nueva); // Invocación correcta del gestor
        gestor.encolarSolicitud(nueva);
        mapaSolicitudes.put(idGenerado, nueva);
        gestorJerarquico.insertarSolicitud(tipo, "General", nueva); // Ajustado a firma de método
        gestorGrafo.agregarRelacion(sucursal, idGenerado);

        System.out.println("Registro exitoso: " + idGenerado);
    }

    // --- MÉTODOS DE SOPORTE ---

    private static void insertarEnListaMaestra(SolicitudBancaria solicitud) {
        Nodo nuevoNodo = new Nodo(solicitud);
        if (cabeza == null) cabeza = nuevoNodo;
        else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) actual = actual.getSiguiente();
            actual.setSiguiente(nuevoNodo);
        }
    }

    private static void buscarRecursivamente() {
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();
        SolicitudBancaria res = buscarPorIdRecursivo(cabeza, id);
        System.out.println(res != null ? "Encontrado: " + res : "No encontrado.");
    }

    public static SolicitudBancaria buscarPorIdRecursivo(Nodo nodo, String id) {
        if (nodo == null) return null;
        if (nodo.getSolicitud().getIdSolicitud().equals(id)) return nodo.getSolicitud();
        return buscarPorIdRecursivo(nodo.getSiguiente(), id);
    }

    private static void procesarAtencion() {
        if (gestor.hayPendientes()) {
            SolicitudBancaria s = gestor.atenderSiguiente();
            s.setEstado("ATENDIDO");
            System.out.println("Atendiendo: " + s.getIdSolicitud());
        }
    }

    private static void consultarProximoCola() {
        if (gestor.hayPendientes()) System.out.println("Siguiente: " + gestor.verPrimero());
    }

    private static void revisarUltimoHistorial() {
        SolicitudBancaria ultimo = gestorHistorial.verUltimo();
        if (ultimo != null) System.out.println("Último: " + ultimo);
        else System.out.println("Historial vacío.");
    }

    private static void verTodoElHistorial() {
        gestorHistorial.mostrarTodo(); // Invocación correcta
    }

    private static void consultarPorFolio() {
        System.out.print("ID: ");
        String id = scanner.nextLine();

        // Verificamos si existe la clave primero
        if (mapaSolicitudes.containsKey(id)) {
            System.out.println(mapaSolicitudes.get(id));
        } else {
            System.out.println("ID no encontrado.");
        }
    }
}
