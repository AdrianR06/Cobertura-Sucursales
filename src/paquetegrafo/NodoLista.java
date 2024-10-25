package paquetegrafo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aiannelli
 */
public class NodoLista {
    private String info;
    private NodoLista next;
    
    public NodoLista (String data) {
        this.info = data;
        this.next = null;
    }    
    public NodoLista (String data, NodoLista next) {
        this.info = data;
        this.next = next;   
    }
    /**
     * @return the clave
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the clave to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the next
     */
    public NodoLista getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(NodoLista next) {
        this.next = next;
    }

}

