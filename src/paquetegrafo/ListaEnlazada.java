package paquetegrafo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VictorB
 */
public class ListaEnlazada {
    private Nodo cabeza;

    // Constructor
    public ListaEnlazada() {
        this.cabeza = null;
    }

     public void agregar(Nodo nodo) {
        if (cabeza == null) {
            cabeza = nodo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
        }
    }
    public void agregar(String valor) {
        Nodo nuevoNodo = new Nodo(valor);
        agregar(nuevoNodo); // Llama al método que acepta un Nodo
    }

    // Método para verificar si un valor está en la lista
    public boolean contiene(String valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getValor().equals(valor)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    // Método para obtener la cabeza de la lista
    public Nodo getCabeza() {
        return cabeza;
    }

    // Método para eliminar un valor de la lista
    public void eliminar(String valor) {
        if (cabeza == null) return; // Lista vacía

        // Si el valor está en la cabeza
        if (cabeza.getValor().equals(valor)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getValor().equals(valor)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            actual = actual.getSiguiente();
        }
    }
    
    public int contarNodos() {
    int contador = 0;
    Nodo actual = cabeza;
    
    while (actual != null) {
        contador++;
        actual = actual.getSiguiente();
    }
    
    return contador;
    }
    
    
}
