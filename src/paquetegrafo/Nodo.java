package paquetegrafo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VictorB
 */
public class Nodo {
    private String valor; 
    private Nodo siguiente;
    private ListaEnlazada adyacentes; // Lista de nodos adyacentes

    public Nodo(String valor) {
        this.valor = valor;
        this.siguiente = null;
        this.adyacentes = new ListaEnlazada(); // Inicializa la lista de adyacentes
    }

    // Método para obtener el valor del nodo
    public String getValor() {
        return valor;
    }

    // Método para obtener el siguiente nodo
    public Nodo getSiguiente() {
        return siguiente;
    }

    // Método para establecer el siguiente nodo
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    // Método para agregar un nodo adyacente
    public void agregarAdyacente(Nodo nodo) {
        adyacentes.agregar(nodo); // Asumiendo que tienes un método agregar en ListaEnlazada
    }

    // Método para obtener la lista de nodos adyacentes
    public ListaEnlazada getAdyacentes() {
        return adyacentes;
    }
}
