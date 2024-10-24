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
    private String linea;
    private Nodo siguiente;

    public Nodo(String valor) {
        this.valor = valor;
        this.siguiente = null;
        this.linea = null;
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

    /**
     * @return the linea
     */
    public String getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }
    
}
