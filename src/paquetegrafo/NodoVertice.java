/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class NodoVertice {
    private int info;
    private NodoVertice next;
    
    public NodoVertice (int info) { 
        this.info = info; 
        this.next = null;     
}

    /**
     * @return the info
     */
    public int getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(int info) {
        this.info = info;
    }

    /**
     * @return the next
     */
    public NodoVertice getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(NodoVertice next) {
        this.next = next;
    }
    
}
