/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class NodoArista {
    private Arista info;
    private NodoArista next;

    public NodoArista(Arista info) {
        this.info = info;
        this.next = null;    
}

    /**
     * @return the info
     */
    public Arista getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(Arista info) {
        this.info = info;
    }

    /**
     * @return the next
     */
    public NodoArista getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(NodoArista next) {
        this.next = next;
    }
    
    
}
