/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VictorB
 */
public class MapaSimple {
    private ListaEnlazada[] tabla;
    private int capacidad;

    public MapaSimple(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        tabla = new ListaEnlazada[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaEnlazada();
        }
    }

    private int hash(String clave) {
        return Math.abs(clave.hashCode()) % capacidad;
    }

    public void agregar(String clave, ListaEnlazada valor) {
        int indice = hash(clave);
        // No hay manejo de colisiones en este ejemplo; puede mejorarse.
        tabla[indice].agregar(clave);
    }

    public boolean contieneClave(String clave) {
        int indice = hash(clave);
        return tabla[indice].contiene(clave);
    }

    
    public ListaEnlazada obtener(String clave) {
        int indice = hash(clave);
        if (tabla[indice].contiene(clave)) {
            return tabla[indice]; // Devuelve la lista enlazada de adyacencias
        }
        return null;
    }
    
    
    public ListaEnlazada obtenerEnIndice(int indice) {
    if (indice >= 0 && indice < capacidad) {
        return tabla[indice];
    }
    return null;
    }
    
    
    public int getCapacidad() {
        return capacidad;
    }
    
    
    public ListaEnlazada todasLasParadas() {
    ListaEnlazada paradas = new ListaEnlazada();
    
    for (int i = 0; i < capacidad; i++) {
        ListaEnlazada listaActual = tabla[i];
        Nodo actual = listaActual.getCabeza();
        
        while (actual != null) {
            if (!paradas.contiene(actual.getValor())) { // Para evitar duplicados
                paradas.agregar(actual.getValor());
            }
            actual = actual.getSiguiente();
        }
    }
    
    return paradas; // Retorna la lista con todas las paradas
    }
    
}
