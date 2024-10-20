package paquetegrafo;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aiannelli
 */
public class Lista {
    private NodoLista inicio;
    
    public Lista () {
    inicio = null;
}

    /**
     * @return the inicio
     */
    public NodoLista getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(NodoLista inicio) {
        this.inicio = inicio;
    }
    
    boolean dirigido; // Indica si es dirigido o no.
    int maxNodos; // Tamaño máximo de la tabla.
    int numVertices; // Número de vértices del grafo.
    Lista listaAdy []; // Vector de listas de adyacencias del grafo.
}
