/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class GrafoLA {
    private int max;
    private int numVertices;
    private Lista[] listaAdy;   

    public GrafoLA(int max) {
        this.max = max; 
        this.numVertices = 0;
        this.listaAdy = new Lista[max];
    }

    //-------getters y setters-------
    
    /**
     * @return the numVertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * @param numVertices the numVertices to set
     */
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the listaVertices
     */
    public Lista[] getListaVertices() {
        return listaAdy;
    }

    /**
     * @param listaVertices the listaVertices to set
     */
    public void setListaVertices(Lista[] listaVertices) {
        this.listaAdy = listaVertices;
    }
    
    //--------metodos---------------
    
    public void insertarVertice(String paradaNueva){
        if (numVertices == max) {
            System.out.println ("Error, se supera el número de nodos máximo del grafo");
        } else {
            listaAdy[numVertices] = new Lista(paradaNueva);
        }
        numVertices += 1;
    }
    
    public int obtenerIndice(String parada){
        for (int i = 0; i < max; i++) {
            NodoLista p = (NodoLista) listaAdy[i].getInicio();
            if (p.getInfo().equals(parada)) {
            return i;
            }
        }
        return -1;
    }
    
    public void insertarArista(String parada1, String parada2){
        int indice1 = obtenerIndice(parada1);
        int indice2 = obtenerIndice(parada2);
        
        listaAdy[indice1].insertarUltimo(parada2);
        listaAdy[indice2].insertarUltimo(parada1);
        
    }
    

}