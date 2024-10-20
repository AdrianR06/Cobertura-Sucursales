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

    public Nodo(String valor) {
        this.valor = valor;
        this.siguiente = null;
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
    
}
