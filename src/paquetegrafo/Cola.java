/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author Adrian
 */
public class Cola {
    private Nodo inicio;
    private Nodo ultimo;
    private int iN;
    
    public Cola() {
        inicio = null;
        ultimo = null;
        iN = 0;
    }

    //----------getters y setters---------
    
    /**
     * @return the inicio
     */
    public Nodo getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the ultimo
     */
    public Nodo getUltimo() {
        return ultimo;
    }

    /**
     * @param ultimo the ultimo to set
     */
    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * @return the iN
     */
    public int getiN() {
        return iN;
    }

    /**
     * @param iN the iN to set
     */
    public void setiN(int iN) {
        this.iN = iN;
    }
    
    //----------primitivas----------
    public void destruirCola(){
        Nodo temporal; // No se usa a proposito para que el recolector de basura de Java lo borre
        while (inicio != null) {
            temporal = inicio;
            inicio.setSiguiente(inicio.getSiguiente());
        }
    }
    
    public void encolar(String x){
        Nodo nuevoNodo = new Nodo(x);

        if (inicio  == null) {
            inicio = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
        }

        ultimo = nuevoNodo;
        iN++;
    }  
    
    public boolean estaVacia(){
        return iN == 0;
    }
    
    public String desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía");
        }

        String dato = inicio.getInfo();
        Nodo temporal = inicio; // Se elimina el primero de la cola
        inicio = inicio.getSiguiente();
        iN--;
        
        return dato; //retorna la info del nodo eliminado
    }
}

