/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class Arista {
    private int destino;
    private String paradaDestino;

    public Arista(int d, String pd) {
        this.destino = d;
        this.paradaDestino = pd;
    }

    /**
     * @return the destino
     */
    public int getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(int destino) {
        this.destino = destino;
    }

    /**
     * @return the paradaDestino
     */
    public String getParadaDestino() {
        return paradaDestino;
    }

    /**
     * @param paradaDestino the paradaDestino to set
     */
    public void setParadaDestino(String paradaDestino) {
        this.paradaDestino = paradaDestino;
    }
    
    
}
