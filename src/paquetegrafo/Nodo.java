package paquetegrafo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aiannelli
 */
public class Nodo {
    private String info;
    private Nodo siguiente;
    
    public Nodo (String data) {
        this.info = data;
        this.siguiente = null;
    }    
    public Nodo (String data, Nodo siguiente) {
        this.info = data;
        this.siguiente = siguiente;   
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
     * @return the siguiente
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}

