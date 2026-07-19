import java.util.ArrayDeque;
import java.util.Deque;

public class GestorHistorial {
    // Usamos Deque como Pila (LIFO)
    private Deque<SolicitudBancaria> pila = new ArrayDeque<>();

    public void apilar(SolicitudBancaria s) {
        pila.push(s); // Agrega al tope
    }

    public SolicitudBancaria verUltimo() {
        return pila.peek(); // Retorna el último sin borrar
    }

    // Si necesitas ver todo el historial en orden LIFO (más reciente primero)
    public void mostrarTodo() {
        for (SolicitudBancaria s : pila) {
            System.out.println(s);
        }
    }
}
