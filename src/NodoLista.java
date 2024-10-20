/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aiannelli
 */
public class NodoLista {
    private int clave;
    private NodoLista sig;
    
    private NodoLista (int dato, NodoLista siguiente) {
    clave = dato;
    sig = siguiente;
}
    /**
     * @return the clave
     */
    public int getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    /**
     * @return the sig
     */
    public NodoLista getSig() {
        return sig;
    }

    /**
     * @param sig the sig to set
     */
    public void setSig(NodoLista sig) {
        this.sig = sig;
    }

}

