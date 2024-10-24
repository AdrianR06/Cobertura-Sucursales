/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class Vertice {
    private int numVertice; 
    private String parada;
    private ListaArista listaAdy;
    
    public Vertice(String parada) {
        this.numVertice = -1;
        this.parada = parada;
        this.listaAdy = new ListaArista();
    }

    /**
     * @return the numVertice
     */
    public int getNumVertice() {
        return numVertice;
    }

    /**
     * @param numVertice the numVertice to set
     */
    public void setNumVertice(int numVertice) {
        this.numVertice = numVertice;
    }

    /**
     * @return the parada
     */
    public String getParada() {
        return parada;
    }

    /**
     * @param parada the parada to set
     */
    public void setParada(String parada) {
        this.parada = parada;
    }

    /**
     * @return the listaAdy
     */
    public ListaArista getListaAdy() {
        return listaAdy;
    }

    /**
     * @param listaAdy the listaAdy to set
     */
    public void setListaAdy(ListaArista listaAdy) {
        this.listaAdy = listaAdy;
    }
    
    
    
}
