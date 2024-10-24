/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class ListaArista {
    private NodoArista inicio;
    private int iN;

    public ListaArista() {
        this.inicio = null;
        this.iN = 0;
    }
    
    public NodoArista buscarUltimo(){
        NodoArista aux = inicio;
        if (getInicio()==null){
            return null;
        }
        while(aux.getNext()!=null){
                aux=aux.getNext();
        }
        return aux;
    }
    public boolean esVacia(){
        return inicio == null;
    }
    
    public NodoArista primero(){ 
        return inicio;
    }
    
    public NodoArista ultimo(){
        return null;
    }
    
    public void preinsertarPrimero(Arista linea){  
        NodoArista nuevo = new NodoArista(linea);                 
        nuevo.setNext(inicio);               
        inicio=nuevo;
        iN++;
    }

    public void insertarUltimo(Arista linea){  
        
        NodoArista ult=buscarUltimo();                    
        NodoArista nuevo = new NodoArista(linea); 
        if(ult == null){
           inicio = nuevo;
        }else{
            ult.setNext(nuevo);
        }
        iN++;   
    }
    
    /**
     * @return the inicio
     */
    public NodoArista getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(NodoArista inicio) {
        this.inicio = inicio;
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
}
